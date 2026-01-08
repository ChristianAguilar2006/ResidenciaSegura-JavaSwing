# ¿Para qué sirven los Controladores?

## 📚 Concepto Simple

Los **controladores** son clases que actúan como intermediarios entre la **interfaz gráfica (Vista)** y la **base de datos**. Su función principal es manejar la **lógica de negocio** de tu aplicación.

## 🎯 ¿Por qué los usamos?

### Sin Controladores (Código Complejo):
```java
// Todo el código SQL y lógica en la ventana - ¡Muy complicado!
public class VentanaLogin extends JFrame {
    private void btnLoginActionPerformed() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = ConexionDB.obtenerConexion();
            String sql = "SELECT * FROM usuarios WHERE correo = ? AND contrasena = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, txtCorreo.getText());
            pstmt.setString(2, txtContrasena.getText());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                // Crear objeto Usuario
                // Verificar rol
                // Abrir dashboard correspondiente
            }
        } catch (Exception e) {
            // Manejar errores
        } finally {
            // Cerrar conexiones
        }
    }
}
```

### Con Controladores (Código Simple):
```java
// En la Ventana (Vista) - Solo se preocupa de la interfaz
public class VentanaLogin extends JFrame {
    private void btnLoginActionPerformed() {
        ControladorLogin controlador = new ControladorLogin();
        Usuario usuario = controlador.autenticarUsuario(txtCorreo.getText(), txtContrasena.getText());
        
        if (usuario != null) {
            // Abrir dashboard según el rol
        } else {
            JOptionPane.showMessageDialog(this, "Credenciales incorrectas");
        }
    }
}

// En el Controlador - Maneja toda la lógica y SQL
public class ControladorLogin {
    public Usuario autenticarUsuario(String correo, String contrasena) {
        // Aquí va todo el código SQL y lógica
        // La ventana no necesita saber cómo funciona
    }
}
```

## 🏗️ Arquitectura del Proyecto

```
┌─────────────┐
│   VISTA     │  ← Interfaz gráfica (Ventanas Swing)
│ (Ventanas)  │     - Solo muestra datos
│             │     - Captura acciones del usuario
└──────┬──────┘
       │
       │ Llama métodos simples
       │
┌──────▼──────────────────┐
│   CONTROLADOR          │  ← Lógica de negocio
│ (ControladorLogin,     │     - Valida datos
│  ControladorPago, etc) │     - Ejecuta consultas SQL
│                        │     - Procesa resultados
└──────┬─────────────────┘
       │
       │ Ejecuta SQL
       │
┌──────▼──────────┐
│  BASE DE DATOS  │  ← MySQL
│  (MySQL)        │     - Almacena información
└─────────────────┘
```

## 📋 Responsabilidades de cada Capa

### 1. **VISTA (Ventanas)**
- ✅ Mostrar información al usuario
- ✅ Capturar datos del usuario (textos, botones, etc.)
- ✅ Mostrar mensajes de éxito/error
- ❌ NO debe tener código SQL
- ❌ NO debe tener lógica compleja

### 2. **CONTROLADOR**
- ✅ Validar datos antes de guardar
- ✅ Ejecutar consultas SQL
- ✅ Procesar resultados de la base de datos
- ✅ Convertir datos de la BD a objetos Java
- ✅ Manejar errores de la base de datos

### 3. **MODELO (Clases como Usuario, Pago, etc.)**
- ✅ Representar datos en Java
- ✅ Guardar información de una entidad

## 💡 Ejemplo Práctico en tu Proyecto

### Ejemplo 1: Login

**VentanaLogin.java** (Vista):
```java
private void btnLoginActionPerformed() {
    // Solo captura los datos y llama al controlador
    String correo = txtCorreo.getText();
    String contrasena = String.valueOf(txtContrasena.getPassword());
    
    ControladorLogin controlador = new ControladorLogin();
    Usuario usuario = controlador.autenticarUsuario(correo, contrasena);
    
    if (usuario != null) {
        // Abrir dashboard según el rol
        abrirDashboard(usuario);
    } else {
        JOptionPane.showMessageDialog(this, "Credenciales incorrectas");
    }
}
```

**ControladorLogin.java** (Controlador):
```java
public Usuario autenticarUsuario(String correo, String contrasena) {
    // Aquí está toda la lógica:
    // 1. Validar que los campos no estén vacíos
    // 2. Conectar a la base de datos
    // 3. Ejecutar consulta SQL
    // 4. Convertir resultado a objeto Usuario
    // 5. Retornar el usuario o null si no existe
    
    Connection conn = ConexionDB.obtenerConexion();
    String sql = "SELECT * FROM usuarios WHERE correo = ? AND contrasena = ?";
    // ... resto del código SQL
}
```

### Ejemplo 2: Crear un Pago

**VentanaGestionPagos.java** (Vista):
```java
private void btnCrearActionPerformed() {
    // Solo captura los datos del formulario
    String tipo = comboTipo.getSelectedItem().toString();
    String monto = txtMonto.getText();
    String fecha = txtFecha.getText();
    
    // Crea un objeto Pago con los datos
    Pago pago = new Pago();
    pago.setTipoServicio(...);
    pago.setMonto(...);
    
    // Llama al controlador para guardar
    ControladorPago controlador = new ControladorPago();
    if (controlador.crearPago(pago)) {
        JOptionPane.showMessageDialog(this, "Pago creado exitosamente");
    }
}
```

**ControladorPago.java** (Controlador):
```java
public boolean crearPago(Pago pago) {
    // Aquí está toda la lógica:
    // 1. Conectar a la base de datos
    // 2. Preparar consulta SQL INSERT
    // 3. Ejecutar la consulta
    // 4. Retornar true si se guardó, false si hubo error
    
    Connection conn = ConexionDB.obtenerConexion();
    String sql = "INSERT INTO pagos (...) VALUES (...)";
    // ... resto del código SQL
}
```

## ✅ Ventajas de Usar Controladores

### 1. **Separación de Responsabilidades**
- La ventana solo se preocupa de mostrar y capturar datos
- El controlador maneja toda la lógica y SQL
- Código más organizado y fácil de entender

### 2. **Reutilización**
```java
// Puedes usar el mismo controlador desde diferentes lugares
ControladorPago controlador = new ControladorPago();

// Desde VentanaGestionPagos
controlador.crearPago(pago);

// Desde otra ventana
controlador.obtenerTodos();

// Desde un reporte
controlador.obtenerPorUsuario(idUsuario);
```

### 3. **Fácil Mantenimiento**
- Si cambia la estructura de la base de datos, solo modificas el controlador
- Las ventanas no necesitan cambios
- Código más fácil de probar y depurar

### 4. **Código Más Limpio**
- Las ventanas tienen menos código
- Es más fácil encontrar errores
- Cada clase tiene una responsabilidad clara

## 🔄 Flujo Completo de un Ejemplo

**Usuario hace clic en "Crear Pago":**

1. **VentanaGestionPagos** captura el evento del botón
2. **VentanaGestionPagos** lee los datos del formulario (tipo, monto, fecha)
3. **VentanaGestionPagos** crea un objeto `Pago` con esos datos
4. **VentanaGestionPagos** llama a `controlador.crearPago(pago)`
5. **ControladorPago** valida los datos
6. **ControladorPago** conecta a la base de datos
7. **ControladorPago** ejecuta `INSERT INTO pagos ...`
8. **ControladorPago** retorna `true` si se guardó correctamente
9. **VentanaGestionPagos** muestra mensaje de éxito
10. **VentanaGestionPagos** actualiza la tabla con los nuevos datos

## 📝 Resumen

| Componente | ¿Qué hace? | Ejemplo |
|------------|------------|---------|
| **Vista** | Muestra interfaz y captura datos | VentanaLogin, VentanaGestionPagos |
| **Controlador** | Maneja lógica y SQL | ControladorLogin, ControladorPago |
| **Modelo** | Representa datos | Usuario, Pago, Reporte, Aviso |
| **Base de Datos** | Almacena información | MySQL (ResidenciaSegura) |

## 🎓 Analogía Simple

Imagina un restaurante:

- **Vista** = El mesero (toma la orden del cliente)
- **Controlador** = El cocinero (prepara la comida según la orden)
- **Modelo** = El plato (representa la comida)
- **Base de Datos** = La cocina (donde están los ingredientes)

El mesero (Vista) no cocina, solo toma la orden y la lleva al cocinero (Controlador). El cocinero sabe cómo preparar la comida usando los ingredientes de la cocina (Base de Datos).

## 🚀 En tu Proyecto

Tienes estos controladores:

1. **ControladorLogin** - Maneja autenticación de usuarios
2. **ControladorPago** - Maneja creación, lectura, actualización y eliminación de pagos
3. **ControladorUsuario** - Maneja gestión de usuarios (crear, editar, eliminar)
4. **ControladorReporte** - Maneja gestión de reportes
5. **ControladorAviso** - Maneja gestión de avisos

Cada uno tiene métodos simples que las ventanas pueden llamar, sin necesidad de saber cómo funcionan internamente.

