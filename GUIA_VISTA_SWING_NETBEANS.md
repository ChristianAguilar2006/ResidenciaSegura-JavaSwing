# 🎨 Guía: Vista en Java Swing y NetBeans

## ¿Qué es la VISTA?

La **VISTA** es la parte visual de tu aplicación - todas las ventanas, botones, campos de texto, tablas, etc. que el usuario ve y con las que interactúa.

En tu proyecto, la Vista está en el paquete `com.residenciasegura.vista` y usa **Java Swing**.

## 📁 Archivos de Vista en tu Proyecto

```
src/com/residenciasegura/vista/
├── VentanaLogin.java          ← Ventana de inicio de sesión
├── DashboardAdmin.java         ← Panel principal del administrador
├── DashboardResidente.java    ← Panel principal del residente
├── DashboardGuardia.java      ← Panel principal del guardia
├── VentanaGestionPagos.java   ← Ventana para gestionar pagos
├── VentanaGestionUsuarios.java ← Ventana para gestionar usuarios
├── VentanaGestionReportes.java ← Ventana para gestionar reportes
├── VentanaGestionAvisos.java  ← Ventana para gestionar avisos
├── VentanaCrearReporte.java   ← Ventana para crear reportes
├── VentanaVerAvisos.java      ← Ventana para ver avisos
└── VentanaGestionReportesGuardia.java ← Ventana del guardia para reportes
```

## 🎨 ¿Qué es Java Swing?

**Java Swing** es una biblioteca de Java para crear interfaces gráficas (GUI - Graphical User Interface). Incluye componentes como:

- **JFrame** - Ventanas principales
- **JButton** - Botones
- **JTextField** - Campos de texto
- **JPasswordField** - Campos de contraseña
- **JTable** - Tablas
- **JLabel** - Etiquetas de texto
- **JComboBox** - Listas desplegables
- **JPanel** - Paneles contenedores
- Y muchos más...

## 👀 Ver la Vista Gráficamente en NetBeans

### Paso 1: Abrir el Diseñador Visual

1. Abre NetBeans
2. Abre tu proyecto `ProyectoDarielAnudesk`
3. En el **Explorador de Proyectos** (lado izquierdo), busca:
   ```
   Source Packages
   └── com.residenciasegura.vista
       └── VentanaLogin.java
   ```
4. Haz **doble clic** en `VentanaLogin.java`
5. En la parte inferior de NetBeans, verás dos pestañas:
   - **Source** (código fuente)
   - **Design** (diseño visual) ← **¡Haz clic aquí!**

### Paso 2: Ver el Diseñador Visual

Cuando hagas clic en **Design**, verás:

```
┌─────────────────────────────────────────┐
│  [Design] [Source]                      │
├─────────────────────────────────────────┤
│                                         │
│  ┌─────────────────────────────────┐   │
│  │                                 │   │
│  │   [Ventana de Login]            │   │
│  │                                 │   │
│  │   Usuario: [___________]        │   │
│  │   Password: [___________]        │   │
│  │                                 │   │
│  │   [  Iniciar Sesión  ]          │   │
│  │                                 │   │
│  └─────────────────────────────────┘   │
│                                         │
│  [Palette] [Properties] [Inspector]     │
└─────────────────────────────────────────┘
```

### Paso 3: Componentes del Diseñador

En el modo **Design** verás:

1. **Vista Principal** (centro)
   - Muestra cómo se ve tu ventana
   - Puedes arrastrar componentes aquí

2. **Palette** (lateral izquierdo)
   - Lista de componentes Swing disponibles
   - Puedes arrastrarlos a tu ventana

3. **Properties** (lateral derecho)
   - Propiedades del componente seleccionado
   - Puedes cambiar tamaño, color, texto, etc.

4. **Inspector** (lateral izquierdo)
   - Estructura jerárquica de componentes
   - Muestra qué componentes están dentro de otros

## 🛠️ Cómo Funciona el Diseñador Visual

### Ejemplo: VentanaLogin.java

Cuando abres `VentanaLogin.java` en modo **Design**, verás:

```
┌──────────────────────────────────────┐
│  RESIDENCIA SEGURA                   │
├──────────────────────────────────────┤
│                                      │
│  Usuario:                            │
│  [________________________]         │
│                                      │
│  Contraseña:                         │
│  [________________________]         │
│                                      │
│  [    Iniciar Sesión    ]            │
│                                      │
└──────────────────────────────────────┘
```

### Componentes que Verás:

1. **JLabel** (Etiquetas)
   - "Usuario:"
   - "Contraseña:"
   - "RESIDENCIA SEGURA"

2. **JTextField** (Campo de texto)
   - Campo para ingresar correo

3. **JPasswordField** (Campo de contraseña)
   - Campo para ingresar contraseña (oculta el texto)

4. **JButton** (Botón)
   - "Iniciar Sesión"

5. **JPanel** (Panel contenedor)
   - Contiene todos los componentes

## 📝 Cómo NetBeans Genera el Código

Cuando diseñas visualmente, NetBeans genera código automáticamente en la sección `//GEN-BEGIN:initComponents`:

```java
private void initComponents() {
    // Este código es generado automáticamente por NetBeans
    jPanel1 = new javax.swing.JPanel();
    lblTitulo = new javax.swing.JLabel();
    txtCorreo = new javax.swing.JTextField();
    txtPassword = new javax.swing.JPasswordField();
    btnIniciarSesion = new javax.swing.JButton();
    
    // Configuración de componentes...
    // Layout y posicionamiento...
}
```

**⚠️ IMPORTANTE:** 
- **NO modifiques** el código entre `//GEN-BEGIN` y `//GEN-END`
- NetBeans lo regenera cada vez que cambias el diseño
- Agrega tu lógica **FUERA** de esas secciones

## 🎯 Ejemplo Práctico: Ver VentanaLogin

### En NetBeans:

1. **Abre** `VentanaLogin.java`
2. **Clic** en la pestaña **Design**
3. **Verás** la ventana visualmente
4. **Selecciona** cualquier componente (botón, campo de texto, etc.)
5. **Mira** el panel **Properties** (derecha) para ver sus propiedades

### Propiedades que Puedes Ver/Cambiar:

- **text** - Texto que muestra
- **font** - Fuente y tamaño
- **background** - Color de fondo
- **foreground** - Color del texto
- **size** - Tamaño (ancho x alto)
- **location** - Posición en la ventana
- **enabled** - Si está habilitado o no

## 🔄 Modo Source vs Design

### Modo Source (Código):
```java
private void initComponents() {
    btnIniciarSesion = new javax.swing.JButton();
    btnIniciarSesion.setText("Iniciar Sesión");
    btnIniciarSesion.addActionListener(this::btnIniciarSesionActionPerformed);
}
```

### Modo Design (Visual):
```
[  Iniciar Sesión  ]  ← Botón visual que puedes ver y arrastrar
```

## 🎨 Componentes Swing Comunes en tu Proyecto

### 1. JFrame (Ventana Principal)
```java
public class VentanaLogin extends javax.swing.JFrame {
    // Es una ventana completa con barra de título
}
```

### 2. JButton (Botón)
```java
btnIniciarSesion = new javax.swing.JButton();
btnIniciarSesion.setText("Iniciar Sesión");
```

### 3. JTextField (Campo de Texto)
```java
txtCorreo = new javax.swing.JTextField();
// El usuario puede escribir aquí
```

### 4. JTable (Tabla)
```java
tablaPagos = new javax.swing.JTable();
// Muestra datos en filas y columnas
```

### 5. JComboBox (Lista Desplegable)
```java
comboTipo = new javax.swing.JComboBox<>();
comboTipo.addItem("mantenimiento");
comboTipo.addItem("seguridad");
```

## 📋 Estructura de una Vista en tu Proyecto

```java
public class VentanaLogin extends javax.swing.JFrame {
    
    // 1. DECLARACIÓN DE COMPONENTES
    private javax.swing.JButton btnIniciarSesion;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JPasswordField txtPassword;
    
    // 2. CONSTRUCTOR
    public VentanaLogin() {
        initComponents();  // Inicializa componentes (generado por NetBeans)
        configurarVentana(); // Tu código personalizado
    }
    
    // 3. INICIALIZACIÓN DE COMPONENTES (Generado por NetBeans)
    private void initComponents() {
        // Código generado automáticamente
        // NO MODIFICAR MANUALMENTE
    }
    
    // 4. CONFIGURACIÓN PERSONALIZADA (Tu código)
    private void configurarVentana() {
        setTitle("Login - Residencia Segura");
        setLocationRelativeTo(null); // Centrar ventana
    }
    
    // 5. EVENTOS (Acciones cuando el usuario hace clic, etc.)
    private void btnIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {
        // Tu lógica aquí
        String correo = txtCorreo.getText();
        // ...
    }
}
```

## 🚀 Cómo Ejecutar y Ver la Vista

### Opción 1: Desde NetBeans
1. Clic derecho en `VentanaLogin.java`
2. Selecciona **Run File**
3. Se abrirá la ventana gráfica

### Opción 2: Ejecutar Proyecto Completo
1. Clic derecho en el proyecto
2. Selecciona **Run**
3. Se ejecutará desde `VentanaLogin.java` (clase principal)

## 💡 Consejos para Usar el Diseñador Visual

1. **Usa Design para diseño rápido**
   - Arrastra componentes
   - Ajusta propiedades visualmente

2. **Usa Source para lógica**
   - Agrega eventos
   - Conecta con controladores

3. **No modifiques código generado**
   - Solo edita fuera de `//GEN-BEGIN` y `//GEN-END`

4. **Guarda frecuentemente**
   - NetBeans guarda cambios automáticamente

## 🎓 Resumen

| Concepto | Descripción |
|----------|-------------|
| **Vista** | Interfaz gráfica (ventanas, botones, etc.) |
| **Java Swing** | Biblioteca para crear GUIs en Java |
| **NetBeans Design** | Herramienta visual para diseñar ventanas |
| **JFrame** | Ventana principal |
| **JButton** | Botón |
| **JTextField** | Campo de texto |
| **initComponents()** | Método generado por NetBeans |

## ✅ Práctica

1. Abre `VentanaLogin.java` en NetBeans
2. Haz clic en la pestaña **Design**
3. Selecciona el botón "Iniciar Sesión"
4. Mira sus propiedades en el panel derecho
5. Cambia el texto del botón y guarda
6. Vuelve a **Source** y verás el cambio en el código

¡Ahora puedes ver y diseñar tus ventanas visualmente en NetBeans! 🎨

