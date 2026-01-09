# 📚 Explicación Completa de la Lógica del Proyecto

## 🎯 Arquitectura General

Tu proyecto sigue el patrón **MVC (Modelo-Vista-Controlador)**:

```
┌─────────────┐
│   VISTA     │  ← Interfaz gráfica (Ventanas Swing)
│ (Ventanas)  │     - Muestra datos al usuario
│             │     - Captura acciones del usuario
└──────┬──────┘
       │
       │ Llama métodos
       │
┌──────▼──────────────────┐
│   CONTROLADOR          │  ← Lógica de negocio
│ (ControladorLogin,     │     - Valida datos
│  ControladorPago, etc) │     - Ejecuta consultas SQL
│                        │     - Procesa resultados
└──────┬─────────────────┘
       │
       │ Usa modelos
       │
┌──────▼──────────┐
│  MODELO         │  ← Representa datos
│ (Usuario, Pago) │     - Clases que representan entidades
└──────┬──────────┘
       │
       │ Consulta
       │
┌──────▼──────────┐
│  BASE DE DATOS │  ← MySQL
│  (MySQL)       │     - Almacena información
└────────────────┘
```

---

## 1️⃣ CAPA DE CONEXIÓN: `ConexionDB.java`

### ¿Qué hace?
Esta clase se encarga de **conectar** tu aplicación Java con la base de datos MySQL.

### Código Explicado:

```java
public class ConexionDB {
    // Datos de conexión
    private static final String URL = "jdbc:mysql://localhost:3306/ResidenciaSegura";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "123456";
```

**Explicación:**
- `URL`: Dirección de la base de datos
  - `jdbc:mysql://` = Protocolo para conectar a MySQL
  - `localhost:3306` = Servidor y puerto (tu computadora, puerto por defecto)
  - `ResidenciaSegura` = Nombre de la base de datos
- `USUARIO`: Usuario de MySQL (normalmente "root")
- `PASSWORD`: Contraseña de MySQL

```java
public static Connection obtenerConexion() {
    try {
        // 1. Cargar el driver de MySQL
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        // 2. Crear la conexión usando los datos
        Connection conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
        
        return conexion;
    } catch (Exception e) {
        // Si hay error, mostrar mensaje y retornar null
        System.out.println("Error al conectar: " + e.getMessage());
        return null;
    }
}
```

**Paso a paso:**
1. **`Class.forName(...)`**: Carga el driver (controlador) de MySQL
2. **`DriverManager.getConnection(...)`**: Crea la conexión usando URL, usuario y contraseña
3. **Retorna** la conexión si todo está bien, o `null` si hay error

**¿Cuándo se usa?**
Cada vez que un controlador necesita hacer una consulta SQL, llama a `ConexionDB.obtenerConexion()`.

---

## 2️⃣ CAPA DE MODELO: Clases como `Usuario.java`, `Pago.java`, etc.

### ¿Qué hace?
Representan las **entidades** de tu base de datos en código Java.

### Ejemplo: `Usuario.java`

```java
public class Usuario {
    // Atributos (propiedades del usuario)
    private int idUsuario;
    private String nombre;
    private String correo;
    private String contrasena;
    private Rol rol;
    private String departamento;
    private String telefono;
    private Timestamp fechaRegistro;
```

**Explicación:**
- Cada atributo corresponde a una columna de la tabla `usuarios` en MySQL
- `private` = Solo esta clase puede acceder directamente
- `Rol` = Es un enum (tipo especial que solo permite valores específicos)

```java
public enum Rol {
    ADMIN, RESIDENTE, GUARDIA;
    
    public String getValor() {
        return this.name().toLowerCase(); // "ADMIN" → "admin"
    }
    
    public static Rol fromString(String valor) {
        if (valor.equalsIgnoreCase("admin")) {
            return ADMIN;
        } else if (valor.equalsIgnoreCase("residente")) {
            return RESIDENTE;
        } else if (valor.equalsIgnoreCase("guardia")) {
            return GUARDIA;
        }
        throw new IllegalArgumentException("Rol no válido: " + valor);
    }
}
```

**Explicación del Enum:**
- `ADMIN, RESIDENTE, GUARDIA`: Valores permitidos
- `getValor()`: Convierte el enum a texto ("admin", "residente", "guardia")
- `fromString()`: Convierte texto a enum ("admin" → ADMIN)

```java
// Getters y Setters
public int getIdUsuario() {
    return idUsuario;
}

public void setIdUsuario(int idUsuario) {
    this.idUsuario = idUsuario;
}
```

**Explicación:**
- **Getter**: Obtiene el valor (`getIdUsuario()`)
- **Setter**: Establece el valor (`setIdUsuario(...)`)
- Permiten acceso controlado a los atributos privados

**¿Cuándo se usa?**
Cuando necesitas crear un objeto Usuario con datos de la base de datos o pasar datos entre capas.

---

## 3️⃣ CAPA DE CONTROLADOR: `ControladorLogin.java`, `ControladorPago.java`, etc.

### ¿Qué hace?
Contiene la **lógica de negocio** y las **consultas SQL**. Es el intermediario entre la Vista y la Base de Datos.

### Ejemplo Completo: `ControladorLogin.java`

```java
public class ControladorLogin {
    
    public Usuario autenticarUsuario(String correo, String contrasena) {
        // 1. VALIDACIÓN INICIAL
        if (correo == null || contrasena == null || 
            correo.trim().isEmpty() || contrasena.trim().isEmpty()) {
            return null; // Si están vacíos, retorna null
        }
```

**Explicación:**
- Valida que los datos no estén vacíos antes de hacer la consulta
- `trim()` elimina espacios al inicio y final
- Si están vacíos, retorna `null` (no hay usuario)

```java
        // 2. DECLARAR VARIABLES PARA RECURSOS
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
```

**Explicación:**
- `Connection`: La conexión a la base de datos
- `PreparedStatement`: La consulta SQL preparada
- `ResultSet`: Los resultados de la consulta
- Se inicializan en `null` para poder cerrarlos en el `finally`

```java
        try {
            // 3. CONECTAR A LA BASE DE DATOS
            conn = ConexionDB.obtenerConexion();
            if (conn == null) {
                return null; // Si no hay conexión, retorna null
            }
```

**Explicación:**
- Llama a `ConexionDB.obtenerConexion()` para obtener la conexión
- Si es `null`, significa que hubo error de conexión

```java
            // 4. PREPARAR LA CONSULTA SQL
            String sql = "SELECT id_usuario, nombre, correo, contrasena, rol, " +
                         "departamento, telefono, fecha_registro " +
                         "FROM usuarios WHERE correo = ? AND contrasena = ?";
            
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, correo.trim());
            pstmt.setString(2, contrasena.trim());
```

**Explicación:**
- `sql`: La consulta SQL que busca un usuario por correo y contraseña
- `?`: Son parámetros que se reemplazan después (evita SQL injection)
- `pstmt.setString(1, ...)`: Reemplaza el primer `?` con el correo
- `pstmt.setString(2, ...)`: Reemplaza el segundo `?` con la contraseña

**¿Por qué usar `?` en lugar de concatenar?**
```java
// ❌ MALO (vulnerable a SQL injection):
String sql = "SELECT * FROM usuarios WHERE correo = '" + correo + "'";

// ✅ BUENO (seguro):
String sql = "SELECT * FROM usuarios WHERE correo = ?";
pstmt.setString(1, correo);
```

```java
            // 5. EJECUTAR LA CONSULTA
            rs = pstmt.executeQuery();
```

**Explicación:**
- `executeQuery()`: Ejecuta la consulta SELECT y retorna los resultados
- Los resultados se guardan en `rs` (ResultSet)

```java
            // 6. PROCESAR LOS RESULTADOS
            if (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setContrasena(rs.getString("contrasena"));
                usuario.setRol(Usuario.Rol.fromString(rs.getString("rol")));
                usuario.setDepartamento(rs.getString("departamento"));
                usuario.setTelefono(rs.getString("telefono"));
                usuario.setFechaRegistro(rs.getTimestamp("fecha_registro"));
                
                return usuario;
            }
```

**Explicación:**
- `rs.next()`: Avanza a la siguiente fila. Retorna `true` si hay datos, `false` si no hay más
- Si hay datos, crea un objeto `Usuario` y llena sus atributos
- `rs.getInt("id_usuario")`: Obtiene el valor de la columna "id_usuario" como int
- `rs.getString("nombre")`: Obtiene el valor de la columna "nombre" como String
- `Usuario.Rol.fromString(...)`: Convierte el texto "admin" al enum ADMIN
- Retorna el usuario completo

```java
        } catch (Exception e) {
            // 7. MANEJAR ERRORES
            System.out.println("Error al validar usuario: " + e.getMessage());
        } finally {
            // 8. CERRAR RECURSOS (SIEMPRE SE EJECUTA)
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
        
        return null; // Si llegó aquí, no encontró el usuario
    }
}
```

**Explicación:**
- `catch`: Captura cualquier error que ocurra
- `finally`: **SIEMPRE** se ejecuta, aunque haya error o no
- Cierra todos los recursos (ResultSet, PreparedStatement, Connection)
- Es importante cerrarlos para liberar memoria y conexiones

---

## 4️⃣ CAPA DE VISTA: `VentanaLogin.java`, `DashboardAdmin.java`, etc.

### ¿Qué hace?
Muestra la **interfaz gráfica** y captura las **acciones del usuario**.

### Ejemplo: `VentanaLogin.java`

```java
public class VentanaLogin extends javax.swing.JFrame {
    private final ControladorLogin controlador;
    
    public VentanaLogin() {
        initComponents(); // Inicializa componentes visuales (generado por NetBeans)
        controlador = new ControladorLogin(); // Crea el controlador
        configurarVentana(); // Configuración personalizada
    }
```

**Explicación:**
- `extends JFrame`: Hereda de JFrame (ventana de Swing)
- `controlador`: Instancia del controlador para hacer las operaciones
- `initComponents()`: Método generado por NetBeans que crea los componentes visuales

```java
    private void btnIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {
        // 1. CAPTURAR DATOS DEL FORMULARIO
        String correo = txtCorreo.getText().trim();
        String password = String.valueOf(txtPassword.getPassword()).trim();
```

**Explicación:**
- Este método se ejecuta cuando el usuario hace clic en el botón "Iniciar Sesión"
- `txtCorreo.getText()`: Obtiene el texto del campo de correo
- `txtPassword.getPassword()`: Obtiene la contraseña (retorna char[], por eso se convierte a String)
- `trim()`: Elimina espacios al inicio y final

```java
        // 2. VALIDAR QUE NO ESTÉN VACÍOS
        if (correo.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Por favor, complete todos los campos", 
                "Campos Vacíos", 
                JOptionPane.WARNING_MESSAGE);
            return; // Sale del método sin hacer nada más
        }
```

**Explicación:**
- Valida que los campos no estén vacíos
- `JOptionPane.showMessageDialog()`: Muestra un mensaje al usuario
- `return`: Sale del método (no continúa)

```java
        // 3. LLAMAR AL CONTROLADOR PARA AUTENTICAR
        Usuario usuario = controlador.autenticarUsuario(correo, password);
```

**Explicación:**
- Llama al método `autenticarUsuario()` del controlador
- El controlador hace toda la lógica (conexión, SQL, etc.)
- Retorna un `Usuario` si las credenciales son correctas, o `null` si no

```java
        // 4. PROCESAR EL RESULTADO
        if (usuario != null) {
            // Si encontró el usuario (login exitoso)
            JOptionPane.showMessageDialog(this, 
                "¡Bienvenido " + usuario.getNombre() + "!", 
                "Acceso Concedido", 
                JOptionPane.INFORMATION_MESSAGE);
            
            // Abrir dashboard según el rol
            abrirDashboard(usuario);
            this.dispose(); // Cierra la ventana de login
        } else {
            // Si no encontró el usuario (login fallido)
            JOptionPane.showMessageDialog(this, 
                "Credenciales incorrectas...", 
                "Error de Autenticación", 
                JOptionPane.ERROR_MESSAGE);
            txtPassword.setText(""); // Limpia el campo de contraseña
        }
    }
```

**Explicación:**
- `if (usuario != null)`: Si el usuario existe (login exitoso)
  - Muestra mensaje de bienvenida
  - Llama a `abrirDashboard()` para abrir la ventana correspondiente
  - `this.dispose()`: Cierra la ventana de login
- `else`: Si el usuario es `null` (login fallido)
  - Muestra mensaje de error
  - Limpia el campo de contraseña

```java
    private void abrirDashboard(Usuario usuario) {
        switch (usuario.getRol()) {
            case ADMIN:
                new DashboardAdmin(usuario).setVisible(true);
                break;
            case RESIDENTE:
                new DashboardResidente(usuario).setVisible(true);
                break;
            case GUARDIA:
                new DashboardGuardia(usuario).setVisible(true);
                break;
        }
    }
```

**Explicación:**
- `switch`: Evalúa el rol del usuario
- Según el rol, abre una ventana diferente:
  - `ADMIN` → `DashboardAdmin`
  - `RESIDENTE` → `DashboardResidente`
  - `GUARDIA` → `DashboardGuardia`
- `setVisible(true)`: Hace visible la ventana

---

## 5️⃣ FLUJO COMPLETO: Ejemplo de Login

### Paso a Paso:

1. **Usuario ingresa datos** en `VentanaLogin`
   ```
   Correo: admin@residencia.com
   Contraseña: admin123
   ```

2. **Usuario hace clic** en "Iniciar Sesión"
   - Se ejecuta `btnIniciarSesionActionPerformed()`

3. **Vista captura datos**
   ```java
   String correo = txtCorreo.getText(); // "admin@residencia.com"
   String password = txtPassword.getPassword(); // "admin123"
   ```

4. **Vista llama al Controlador**
   ```java
   Usuario usuario = controlador.autenticarUsuario(correo, password);
   ```

5. **Controlador valida datos**
   ```java
   if (correo.isEmpty() || password.isEmpty()) return null;
   ```

6. **Controlador conecta a la BD**
   ```java
   Connection conn = ConexionDB.obtenerConexion();
   ```

7. **Controlador ejecuta SQL**
   ```sql
   SELECT * FROM usuarios 
   WHERE correo = 'admin@residencia.com' 
   AND contrasena = 'admin123'
   ```

8. **Controlador procesa resultados**
   ```java
   if (rs.next()) {
       Usuario usuario = new Usuario();
       usuario.setNombre(rs.getString("nombre"));
       // ... llena todos los campos
       return usuario;
   }
   ```

9. **Controlador retorna Usuario** a la Vista

10. **Vista procesa resultado**
    ```java
    if (usuario != null) {
        abrirDashboard(usuario); // Abre DashboardAdmin
    }
    ```

11. **Se abre DashboardAdmin** y se cierra VentanaLogin

---

## 6️⃣ OPERACIONES CRUD: Ejemplo con `ControladorPago.java`

### CREATE (Crear):

```java
public boolean crearPago(Pago pago) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    
    try {
        conn = ConexionDB.obtenerConexion();
        if (conn == null) return false;
        
        // SQL para INSERTAR
        String sql = "INSERT INTO pagos (id_usuario, tipo_servicio, monto, fecha_pago, estado, metodo_pago) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, pago.getIdUsuario());
        pstmt.setString(2, pago.getTipoServicio().getValor());
        pstmt.setBigDecimal(3, pago.getMonto());
        pstmt.setDate(4, pago.getFechaPago());
        pstmt.setString(5, pago.getEstado().getValor());
        pstmt.setString(6, pago.getMetodoPago().getValor());
        
        int filas = pstmt.executeUpdate(); // Ejecuta INSERT
        return filas > 0; // Retorna true si se insertó al menos una fila
    } catch (Exception e) {
        System.out.println("Error al crear pago: " + e.getMessage());
        return false;
    } finally {
        cerrarRecursos(null, pstmt, conn);
    }
}
```

**Explicación:**
- `INSERT INTO`: Comando SQL para insertar datos
- `executeUpdate()`: Ejecuta INSERT, UPDATE o DELETE (no SELECT)
- Retorna el número de filas afectadas
- `filas > 0`: Si es mayor que 0, significa que se insertó correctamente

### READ (Leer):

```java
public List<Pago> obtenerTodos() {
    List<Pago> pagos = new ArrayList<>();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    try {
        conn = ConexionDB.obtenerConexion();
        if (conn == null) return pagos;
        
        // SQL para SELECT
        String sql = "SELECT p.*, u.nombre as nombre_usuario FROM pagos p " +
                     "INNER JOIN usuarios u ON p.id_usuario = u.id_usuario " +
                     "ORDER BY p.fecha_pago DESC";
        
        pstmt = conn.prepareStatement(sql);
        rs = pstmt.executeQuery(); // Ejecuta SELECT
        
        // Procesar cada fila del resultado
        for (Pago pago : procesarResultSet(rs)) {
            pagos.add(pago);
        }
    } catch (Exception e) {
        System.out.println("Error al obtener pagos: " + e.getMessage());
    } finally {
        cerrarRecursos(rs, pstmt, conn);
    }
    
    return pagos; // Retorna la lista de pagos
}
```

**Explicación:**
- `SELECT`: Comando SQL para leer datos
- `INNER JOIN`: Une dos tablas (pagos con usuarios)
- `ORDER BY`: Ordena los resultados
- `executeQuery()`: Ejecuta SELECT y retorna ResultSet
- `procesarResultSet()`: Convierte cada fila en un objeto Pago
- Retorna una lista de pagos

### UPDATE (Actualizar):

```java
public boolean actualizarPago(Pago pago) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    
    try {
        conn = ConexionDB.obtenerConexion();
        if (conn == null) return false;
        
        // SQL para ACTUALIZAR
        String sql = "UPDATE pagos SET tipo_servicio = ?, monto = ?, estado = ? " +
                     "WHERE id_pago = ?";
        
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, pago.getTipoServicio().getValor());
        pstmt.setBigDecimal(2, pago.getMonto());
        pstmt.setString(3, pago.getEstado().getValor());
        pstmt.setInt(4, pago.getIdPago()); // WHERE id_pago = ?
        
        int filas = pstmt.executeUpdate();
        return filas > 0;
    } catch (Exception e) {
        System.out.println("Error al actualizar pago: " + e.getMessage());
        return false;
    } finally {
        cerrarRecursos(null, pstmt, conn);
    }
}
```

**Explicación:**
- `UPDATE`: Comando SQL para actualizar datos
- `SET`: Establece nuevos valores
- `WHERE`: Condición para saber qué fila actualizar
- `executeUpdate()`: Ejecuta la actualización

### DELETE (Eliminar):

```java
public boolean eliminarPago(int idPago) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    
    try {
        conn = ConexionDB.obtenerConexion();
        if (conn == null) return false;
        
        // SQL para ELIMINAR
        String sql = "DELETE FROM pagos WHERE id_pago = ?";
        
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, idPago);
        
        int filas = pstmt.executeUpdate();
        return filas > 0;
    } catch (Exception e) {
        System.out.println("Error al eliminar pago: " + e.getMessage());
        return false;
    } finally {
        cerrarRecursos(null, pstmt, conn);
    }
}
```

**Explicación:**
- `DELETE FROM`: Comando SQL para eliminar datos
- `WHERE`: Condición para saber qué fila eliminar
- `executeUpdate()`: Ejecuta la eliminación

---

## 7️⃣ PATRÓN COMÚN EN TODOS LOS CONTROLADORES

Todos los controladores siguen este patrón:

```java
public TipoRetorno metodo(TipoParametro parametro) {
    // 1. Declarar variables
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    try {
        // 2. Conectar
        conn = ConexionDB.obtenerConexion();
        if (conn == null) return valorPorDefecto;
        
        // 3. Preparar SQL
        String sql = "SELECT/INSERT/UPDATE/DELETE ...";
        pstmt = conn.prepareStatement(sql);
        pstmt.setTipo(1, valor);
        
        // 4. Ejecutar
        if (esSELECT) {
            rs = pstmt.executeQuery();
            // Procesar resultados
        } else {
            int filas = pstmt.executeUpdate();
            return filas > 0;
        }
        
    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
        return valorPorDefecto;
    } finally {
        // 5. Cerrar recursos
        cerrarRecursos(rs, pstmt, conn);
    }
}
```

---

## 📋 RESUMEN DE CONCEPTOS CLAVE

| Concepto | ¿Qué es? | Ejemplo |
|----------|----------|---------|
| **Connection** | Conexión a la base de datos | `Connection conn = ConexionDB.obtenerConexion()` |
| **PreparedStatement** | Consulta SQL preparada | `PreparedStatement pstmt = conn.prepareStatement(sql)` |
| **ResultSet** | Resultados de una consulta SELECT | `ResultSet rs = pstmt.executeQuery()` |
| **executeQuery()** | Ejecuta SELECT | Retorna ResultSet |
| **executeUpdate()** | Ejecuta INSERT/UPDATE/DELETE | Retorna número de filas afectadas |
| **rs.next()** | Avanza a la siguiente fila | Retorna true si hay datos |
| **rs.getInt()** | Obtiene valor como int | `rs.getInt("id_usuario")` |
| **rs.getString()** | Obtiene valor como String | `rs.getString("nombre")` |
| **try-catch-finally** | Manejo de errores | try = código, catch = errores, finally = siempre se ejecuta |

---

## 🎓 CONSEJOS PARA ENTENDER EL CÓDIGO

1. **Sigue el flujo**: Vista → Controlador → Base de Datos
2. **Lee los mensajes de consola**: Te ayudan a entender qué está pasando
3. **Revisa el SQL**: Entender SQL te ayuda a entender la lógica
4. **Prueba paso a paso**: Pon `System.out.println()` para ver qué valores tienen las variables
5. **No te preocupes por los detalles**: Primero entiende el flujo general, luego los detalles

---

## ✅ CHECKLIST DE COMPRENSIÓN

- [ ] ¿Entiendes cómo se conecta a la base de datos?
- [ ] ¿Entiendes qué hace un Modelo?
- [ ] ¿Entiendes qué hace un Controlador?
- [ ] ¿Entiendes qué hace una Vista?
- [ ] ¿Entiendes cómo funciona el login completo?
- [ ] ¿Entiendes las operaciones CRUD (Create, Read, Update, Delete)?
- [ ] ¿Entiendes por qué se usa try-catch-finally?

¡Si entiendes estos conceptos, ya dominas la lógica del proyecto! 🎉

