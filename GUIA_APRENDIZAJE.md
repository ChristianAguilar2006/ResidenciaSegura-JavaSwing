# 📚 Guía de Aprendizaje - Residencia Segura

## 🎯 Conceptos Básicos que Debes Entender

### 1. **Packages (Paquetes)**
Los packages organizan las clases en carpetas lógicas:
- `modelo` = Las clases que representan datos (Usuario)
- `dao` = Las clases que hablan con la base de datos
- `vista` = Las ventanas gráficas (Swing)
- `controlador` = La lógica que conecta vista con datos
- `util` = Utilidades (conexión a BD)

### 2. **Clase Usuario (Modelo)**
```java
public class Usuario {
    // Atributos privados (datos del usuario)
    private int idUsuario;
    private String nombre;
    private String correo;
    // ...
    
    // Getters y Setters (métodos para leer y escribir)
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}
```
**¿Qué hace?** Representa un usuario con sus datos.

### 3. **Enum Rol**
```java
public enum Rol {
    ADMIN, RESIDENTE, GUARDIA
}
```
**¿Qué hace?** Define los 3 tipos de roles posibles. Es como una lista fija de opciones.

### 4. **ConexionDB (Utilidad)**
```java
public static Connection obtenerConexion() {
    // Carga el driver de MySQL
    Class.forName("com.mysql.cj.jdbc.Driver");
    // Crea la conexión
    return DriverManager.getConnection(URL, USUARIO, PASSWORD);
}
```
**¿Qué hace?** Conecta Java con MySQL. Es como abrir una puerta a la base de datos.

### 5. **UsuarioDAO (Acceso a Datos)**
```java
public Usuario validarUsuario(String correo, String contrasena) {
    // 1. Preparar consulta SQL
    String sql = "SELECT * FROM usuarios WHERE correo = ? AND contrasena = ?";
    
    // 2. Conectar a la BD
    Connection conn = ConexionDB.obtenerConexion();
    
    // 3. Preparar la consulta
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, correo);      // Reemplaza el primer ?
    pstmt.setString(2, contrasena);   // Reemplaza el segundo ?
    
    // 4. Ejecutar y obtener resultados
    ResultSet rs = pstmt.executeQuery();
    
    // 5. Si hay resultado, crear objeto Usuario
    if (rs.next()) {
        Usuario usuario = new Usuario();
        usuario.setNombre(rs.getString("nombre"));
        // ...
        return usuario;
    }
    
    return null; // No se encontró
}
```
**¿Qué hace?** Busca un usuario en la base de datos y lo retorna como objeto Java.

### 6. **ControladorLogin**
```java
public Usuario autenticarUsuario(String correo, String contrasena) {
    // Simplemente llama al DAO
    return usuarioDAO.validarUsuario(correo, contrasena);
}
```
**¿Qué hace?** Es el intermediario entre la ventana y la base de datos.

### 7. **VentanaLogin (Swing)**
```java
public class VentanaLogin extends JFrame {
    // Componentes Swing
    private JTextField txtCorreo;
    private JPasswordField txtPassword;
    private JButton btnIniciarSesion;
    
    // Cuando se hace clic en el botón
    private void btnIniciarSesionActionPerformed(...) {
        String correo = txtCorreo.getText();
        String password = String.valueOf(txtPassword.getPassword());
        
        Usuario usuario = controlador.autenticarUsuario(correo, password);
        
        if (usuario != null) {
            // Abrir dashboard según el rol
        }
    }
}
```
**¿Qué hace?** La ventana gráfica que el usuario ve y usa.

---

## 🔄 Flujo del Programa

```
1. Usuario abre VentanaLogin
   ↓
2. Usuario ingresa correo y contraseña
   ↓
3. Click en "Iniciar Sesión"
   ↓
4. VentanaLogin llama a ControladorLogin
   ↓
5. ControladorLogin llama a UsuarioDAO
   ↓
6. UsuarioDAO consulta MySQL
   ↓
7. Si encuentra usuario, retorna objeto Usuario
   ↓
8. VentanaLogin recibe el usuario y abre el Dashboard correspondiente
```

---

## 📝 Conceptos Clave Simplificados

### **PreparedStatement**
- Es una forma SEGURA de hacer consultas SQL
- Los `?` se reemplazan con valores reales
- Previene inyección SQL

### **ResultSet**
- Es como una tabla de resultados
- `rs.next()` avanza a la siguiente fila
- `rs.getString("nombre")` obtiene el valor de la columna "nombre"

### **try-catch-finally**
```java
try {
    // Código que puede fallar
} catch (Exception e) {
    // Qué hacer si falla
} finally {
    // Código que SIEMPRE se ejecuta (cerrar conexiones)
}
```

### **Interfaces (IUsuarioDAO)**
- Es como un contrato: "Todas las clases que implementen esto DEBEN tener estos métodos"
- Permite cambiar la implementación sin cambiar el código que la usa

---

## 🎓 Orden de Estudio (1 Día)

### **Mañana (3-4 horas)**
1. ✅ Leer `Usuario.java` - Entender qué es un modelo
2. ✅ Leer `ConexionDB.java` - Entender cómo se conecta a MySQL
3. ✅ Leer `UsuarioDAO.java` - Entender cómo se consulta la BD
4. ✅ Probar hacer una consulta manual en MySQL

### **Tarde (3-4 horas)**
5. ✅ Leer `ControladorLogin.java` - Entender el flujo
6. ✅ Leer `VentanaLogin.java` - Entender Swing básico
7. ✅ Ejecutar el programa y probar el login
8. ✅ Modificar algo pequeño (cambiar un mensaje, color, etc.)

### **Noche (2 horas)**
9. ✅ Leer los Dashboards - Ver cómo se diferencian por rol
10. ✅ Intentar agregar un botón nuevo o cambiar algo

---

## 💡 Tips para Aprender Rápido

1. **Ejecuta el código mientras lo lees** - Ver qué pasa en tiempo real
2. **Haz cambios pequeños** - Cambia un mensaje, un color, un texto
3. **Usa System.out.println()** - Para ver qué valores tienen las variables
4. **Lee los comentarios** - Están ahí para ayudarte
5. **No te preocupes por entender TODO** - Entiende el flujo general primero

---

## ❓ Preguntas que Debes Poder Responder

- ¿Qué hace cada package?
- ¿Cómo fluyen los datos desde la ventana hasta la BD?
- ¿Qué es un PreparedStatement y por qué se usa?
- ¿Qué diferencia hay entre un modelo, un DAO y un controlador?
- ¿Cómo funciona el login paso a paso?

---

## 🚀 Próximos Pasos

Una vez entiendas esto, puedes:
- Agregar más funcionalidades (CRUD de usuarios)
- Crear ventanas para pagos y reportes
- Mejorar el diseño de las ventanas
- Agregar validaciones

¡Éxito aprendiendo! 🎉

