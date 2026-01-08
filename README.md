# Sistema de Gestión Residencial - Residencia Segura

Sistema de gestión residencial desarrollado en Java Swing con MySQL para la administración de usuarios, pagos, reportes y avisos.

## Estructura del Proyecto

El proyecto está organizado siguiendo el patrón de arquitectura en capas:

```
src/com/residenciasegura/
├── modelo/          # Entidades del dominio (Usuario, Pago, Reporte, Aviso)
├── dao/            # Interfaces y implementaciones de acceso a datos
├── util/           # Utilidades (Conexión DB, etc.)
├── vista/          # Interfaces gráficas (Ventanas Swing)
└── controlador/    # Lógica de negocio y controladores
```

## Requisitos Previos

1. **Java JDK 11 o superior**
2. **MySQL Server** (versión 5.7 o superior)
3. **Apache NetBeans IDE** (con soporte Maven)
4. **Maven** (incluido en NetBeans)

## Configuración de la Base de Datos

1. Ejecuta el script SQL proporcionado para crear la base de datos `ResidenciaSegura`
2. Asegúrate de que MySQL esté corriendo en `localhost:3306`
3. Verifica las credenciales de acceso en `ConexionDB.java`:
   - Usuario: `root` (por defecto)
   - Contraseña: `""` (vacía por defecto)
   - URL: `jdbc:mysql://localhost:3306/ResidenciaSegura`

## Configuración del Proyecto en NetBeans (Maven)

1. **Abre NetBeans**
2. **Abre el Proyecto:**
   - File → Open Project
   - Selecciona la carpeta `ProyectoDarielAnudesk`
   - NetBeans reconocerá automáticamente que es un proyecto Maven
3. **Descarga de Dependencias:**
   - NetBeans descargará automáticamente el driver de MySQL desde Maven
   - Si no se descarga automáticamente: Click derecho en el proyecto → Dependencies → Download Declared Dependencies
4. **Ejecuta el Proyecto:**
   - Click derecho en `VentanaLogin.java` → Run File
   - O presiona `Shift + F6`

**¡El driver de MySQL ya viene incluido en el `pom.xml`!** No necesitas descargarlo manualmente.

## Estructura de Packages

### Modelo (`com.residenciasegura.modelo`)
- **Usuario.java**: Entidad que representa un usuario del sistema con sus roles (ADMIN, RESIDENTE, GUARDIA)

### DAO (`com.residenciasegura.dao`)
- **IUsuarioDAO.java**: Interfaz para operaciones de acceso a datos de usuarios
- **UsuarioDAO.java**: Implementación del DAO de usuarios

### Utilidades (`com.residenciasegura.util`)
- **ConexionDB.java**: Clase singleton para gestionar la conexión a MySQL

### Vista (`com.residenciasegura.vista`)
- **VentanaLogin.java**: Ventana de inicio de sesión
- **DashboardAdmin.java**: Panel de administración
- **DashboardResidente.java**: Panel del residente
- **DashboardGuardia.java**: Panel del guardia

### Controlador (`com.residenciasegura.controlador`)
- **ControladorLogin.java**: Controlador para la lógica de autenticación

## Funcionalidades Implementadas

### ✅ Sistema de Autenticación
- Login con validación de credenciales
- Redirección según rol del usuario
- Dashboards diferenciados por rol

### 🔄 Próximas Funcionalidades
- Gestión de usuarios (CRUD)
- Gestión de pagos
- Gestión de reportes
- Gestión de avisos

## Roles del Sistema

1. **Administrador (admin)**
   - Gestión completa de usuarios
   - Gestión de pagos
   - Gestión de reportes
   - Gestión de avisos

2. **Residente (residente)**
   - Ver sus pagos
   - Crear reportes
   - Ver avisos

3. **Guardia (guardia)**
   - Ver reportes
   - Atender reportes
   - Ver avisos

## Ejecución

1. Asegúrate de que MySQL esté corriendo
2. Ejecuta la clase principal `VentanaLogin` desde NetBeans
3. Inicia sesión con las credenciales de un usuario registrado en la base de datos

## Notas Importantes

- La contraseña se almacena en texto plano (se recomienda implementar hash en producción)
- Ajusta las credenciales de conexión según tu configuración de MySQL
- El proyecto utiliza el patrón DAO para separar la lógica de acceso a datos

## Autor

DARIX - Proyecto de Aprendizaje Java

