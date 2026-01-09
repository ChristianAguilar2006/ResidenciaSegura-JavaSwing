# 🔄 Cómo Funciona la Comunicación: Admin Crea Aviso → Residente Lo Ve

## 🎯 La Respuesta Simple

**La BASE DE DATOS es el punto de comunicación entre ambos.**

```
Admin crea aviso → Se guarda en MySQL → Residente consulta MySQL → Ve el aviso
```

---

## 📊 Flujo Completo Paso a Paso

### PASO 1: Admin Crea un Aviso

**Archivo:** `VentanaGestionAvisos.java` (línea 255-284)

```java
// Admin llena el formulario y hace clic en "Crear"
private void btnCrearActionPerformed(...) {
    // 1. Crea un objeto Aviso con los datos
    Aviso aviso = new Aviso();
    aviso.setTitulo("Mantenimiento Programado");
    aviso.setMensaje("Se realizará mantenimiento...");
    aviso.setTipo(Aviso.TipoAviso.MANTENIMIENTO);
    aviso.setActivo(true);
    
    // 2. Llama al controlador para guardarlo
    controlador.crearAviso(aviso);
}
```

### PASO 2: Controlador Guarda en la Base de Datos

**Archivo:** `ControladorAviso.java` (línea 107-131)

```java
public boolean crearAviso(Aviso aviso) {
    // 1. Conecta a MySQL
    Connection conn = ConexionDB.obtenerConexion();
    
    // 2. Prepara el SQL INSERT
    String sql = "INSERT INTO avisos (id_administrador, titulo, mensaje, tipo, activo) " +
                 "VALUES (?, ?, ?, ?, ?)";
    
    // 3. Ejecuta el INSERT
    pstmt.setInt(1, aviso.getIdAdministrador());
    pstmt.setString(2, aviso.getTitulo());
    pstmt.setString(3, aviso.getMensaje());
    pstmt.setString(4, aviso.getTipo().getValor());
    pstmt.setBoolean(5, aviso.isActivo());
    
    pstmt.executeUpdate(); // ← AQUÍ SE GUARDA EN LA BASE DE DATOS
    
    return true;
}
```

**¿Qué pasa aquí?**
- El aviso se **GUARDA FÍSICAMENTE** en la tabla `avisos` de MySQL
- Queda almacenado en el disco duro de tu computadora
- Cualquier usuario puede consultarlo después

### PASO 3: Residente Abre "Ver Avisos"

**Archivo:** `DashboardResidente.java` (línea ~110)

```java
// Residente hace clic en "Ver Avisos"
private void btnVerAvisosActionPerformed(...) {
    // Abre la ventana VentanaVerAvisos
    new VentanaVerAvisos(usuarioActual).setVisible(true);
}
```

### PASO 4: Ventana Carga los Avisos desde la Base de Datos

**Archivo:** `VentanaVerAvisos.java` (línea 43-59)

```java
private void cargarAvisos() {
    // 1. Llama al controlador para obtener avisos ACTIVOS
    List<Aviso> avisos = controlador.obtenerActivos();
    
    // 2. Muestra cada aviso en la tabla
    for (Aviso aviso : avisos) {
        Object[] fila = {
            aviso.getIdAviso(),
            aviso.getTitulo(),
            aviso.getTipo().getValor(),
            aviso.getFechaPublicacion(),
            aviso.isActivo() ? "Activo" : "Inactivo"
        };
        modeloTabla.addRow(fila); // Agrega a la tabla visual
    }
}
```

### PASO 5: Controlador Consulta la Base de Datos

**Archivo:** `ControladorAviso.java` (línea 48-76)

```java
public List<Aviso> obtenerActivos() {
    List<Aviso> avisos = new ArrayList<>();
    
    // 1. Conecta a MySQL (LA MISMA BASE DE DATOS)
    Connection conn = ConexionDB.obtenerConexion();
    
    // 2. Prepara el SQL SELECT
    String sql = "SELECT a.*, u.nombre as nombre_administrador FROM avisos a " +
                 "INNER JOIN usuarios u ON a.id_administrador = u.id_usuario " +
                 "WHERE a.activo = TRUE " +  // ← Solo avisos ACTIVOS
                 "ORDER BY a.fecha_publicacion DESC";
    
    // 3. Ejecuta el SELECT
    ResultSet rs = pstmt.executeQuery();
    
    // 4. Convierte cada fila en un objeto Aviso
    for (Aviso aviso : procesarResultSet(rs)) {
        avisos.add(aviso);
    }
    
    return avisos; // Retorna la lista de avisos
}
```

**¿Qué pasa aquí?**
- Consulta la **MISMA BASE DE DATOS** donde el admin guardó el aviso
- Busca todos los avisos con `activo = TRUE`
- Los retorna como una lista de objetos `Aviso`

---

## 🗄️ La Base de Datos como Punto de Comunicación

### Visualización:

```
┌─────────────────────────────────────────────────────────┐
│              BASE DE DATOS (MySQL)                     │
│                                                         │
│  Tabla: avisos                                         │
│  ┌────┬──────────────┬──────────────┬────────┐        │
│  │ ID │ Título       │ Mensaje      │ Activo │        │
│  ├────┼──────────────┼──────────────┼────────┤        │
│  │ 1  │ Mantenimiento│ Se realizará │ TRUE   │ ← Admin lo creó
│  │ 2  │ Reunión      │ Se convoca...│ TRUE   │ ← Admin lo creó
│  └────┴──────────────┴──────────────┴────────┘        │
│                                                         │
└─────────────────────────────────────────────────────────┘
         ▲                          ▲
         │                          │
         │                          │
    ┌────┴────┐              ┌──────┴──────┐
    │  ADMIN  │              │  RESIDENTE  │
    │         │              │             │
    │ INSERT  │              │   SELECT    │
    │ (Guarda)│              │  (Consulta) │
    └─────────┘              └─────────────┘
```

---

## 🔍 Ejemplo Práctico con Código

### Admin Crea Aviso:

```java
// Admin en VentanaGestionAvisos.java
Aviso aviso = new Aviso();
aviso.setTitulo("Corte de Agua");
aviso.setMensaje("Habrá corte de agua mañana");
aviso.setActivo(true);

controlador.crearAviso(aviso); // Guarda en MySQL
```

**SQL que se ejecuta:**
```sql
INSERT INTO avisos (titulo, mensaje, activo) 
VALUES ('Corte de Agua', 'Habrá corte de agua mañana', TRUE);
```

**Resultado en MySQL:**
```
id_aviso | titulo        | mensaje                    | activo
---------|---------------|----------------------------|--------
    1    | Corte de Agua | Habrá corte de agua mañana | TRUE
```

### Residente Ve Avisos:

```java
// Residente en VentanaVerAvisos.java
List<Aviso> avisos = controlador.obtenerActivos(); // Consulta MySQL
```

**SQL que se ejecuta:**
```sql
SELECT * FROM avisos WHERE activo = TRUE;
```

**Resultado:**
- El controlador retorna una lista con el aviso "Corte de Agua"
- La ventana lo muestra en la tabla

---

## 💡 Conceptos Clave

### 1. **Persistencia de Datos**
- Los datos se guardan en el disco duro (MySQL)
- No se pierden cuando cierras la aplicación
- Están disponibles para todos los usuarios

### 2. **Comunicación Indirecta**
- Admin y Residente **NO se comunican directamente**
- Ambos se comunican con la **BASE DE DATOS**
- La BD actúa como intermediario

### 3. **Mismo Controlador, Diferentes Métodos**
```java
// Admin usa:
controlador.crearAviso(aviso);  // INSERT

// Residente usa:
controlador.obtenerActivos();   // SELECT
```

Ambos usan el **mismo controlador** (`ControladorAviso`), pero métodos diferentes.

---

## 🔄 Flujo Completo Visual

```
┌─────────────────────────────────────────────────────────────┐
│                    ADMIN                                    │
│                                                             │
│  1. Abre VentanaGestionAvisos                              │
│  2. Llena formulario:                                      │
│     - Título: "Mantenimiento"                              │
│     - Mensaje: "Se realizará..."                           │
│  3. Clic en "Crear"                                        │
│  4. Llama: controlador.crearAviso(aviso)                   │
│                                                             │
└───────────────────────┬─────────────────────────────────────┘
                        │
                        │ INSERT INTO avisos ...
                        ▼
┌─────────────────────────────────────────────────────────────┐
│              BASE DE DATOS (MySQL)                         │
│                                                             │
│  Tabla: avisos                                             │
│  ┌────┬──────────────┬──────────────┬────────┐            │
│  │ ID │ Título       │ Mensaje      │ Activo │            │
│  ├────┼──────────────┼──────────────┼────────┤            │
│  │ 1  │ Mantenimiento│ Se realizará │ TRUE   │ ← Guardado │
│  └────┴──────────────┴──────────────┴────────┘            │
│                                                             │
└───────────────────────┬─────────────────────────────────────┘
                        │
                        │ SELECT * FROM avisos WHERE activo=TRUE
                        ▼
┌─────────────────────────────────────────────────────────────┐
│                  RESIDENTE                                  │
│                                                             │
│  1. Abre VentanaVerAvisos                                  │
│  2. Llama: controlador.obtenerActivos()                    │
│  3. Recibe lista de avisos                                 │
│  4. Muestra en tabla:                                      │
│     ┌────────────────────────────────────┐                │
│     │ Mantenimiento │ Se realizará...    │                │
│     └────────────────────────────────────┘                │
│                                                             │
└─────────────────────────────────────────────────────────────┘
```

---

## 📝 Resumen Simple

1. **Admin crea aviso** → Se guarda en MySQL (tabla `avisos`)
2. **Residente abre "Ver Avisos"** → Consulta MySQL (tabla `avisos`)
3. **MySQL retorna los avisos** → Residente los ve en la tabla

**La base de datos es el "mensajero" entre Admin y Residente.**

---

## 🎓 Analogía Simple

Imagina un **tablero de anuncios**:

- **Admin** escribe un aviso y lo **PINCHA** en el tablero (MySQL)
- **Residente** va al tablero y **LEE** los avisos (MySQL)
- El tablero (MySQL) es donde ambos se encuentran

¡Así funciona! La base de datos es el punto de encuentro. 🎯

