# 🚀 Instrucciones para NetBeans con Maven

## ✅ Configuración Rápida

### Paso 1: Abrir el Proyecto en NetBeans

1. Abre **Apache NetBeans**
2. Ve a **File → Open Project**
3. Selecciona la carpeta `ProyectoDarielAnudesk`
4. NetBeans reconocerá automáticamente que es un proyecto **Maven**
5. Click en **Open Project**

### Paso 2: Descargar Dependencias (Automático)

NetBeans debería descargar automáticamente las dependencias, pero si no:

1. Click derecho en el proyecto → **Dependencies → Download Declared Dependencies**
2. O espera a que NetBeans lo haga automáticamente al abrir el proyecto

**El driver de MySQL (`mysql-connector-j`) ya está configurado en el `pom.xml`** - No necesitas descargarlo manualmente.

### Paso 3: Configurar la Base de Datos

1. Asegúrate de que **MySQL esté corriendo**
2. Ejecuta el script SQL para crear la base de datos `ResidenciaSegura`
3. Ejecuta `datos_ejemplo.sql` para insertar datos de prueba

### Paso 4: Ejecutar el Proyecto

**Opción A: Ejecutar desde NetBeans**
- Click derecho en `VentanaLogin.java`
- Selecciona **Run File** (o presiona `Shift + F6`)

**Opción B: Ejecutar desde Maven**
- Click derecho en el proyecto → **Run → Run Maven Goal**
- Escribe: `exec:java`
- Click en **Run**

---

## 📋 Estructura del Proyecto Maven

```
ProyectoDarielAnudesk/
├── pom.xml                    ← Archivo de configuración Maven (con dependencias)
├── src/
│   └── com/
│       └── residenciasegura/
│           ├── modelo/        ← Clases de datos
│           ├── dao/           ← Acceso a base de datos
│           ├── util/         ← Utilidades (ConexionDB)
│           ├── vista/        ← Ventanas Swing
│           └── controlador/  ← Lógica de negocio
└── target/                    ← Carpeta generada por Maven (compilados)
```

---

## 🔍 Verificar que Maven Funciona

1. En NetBeans, ve a la pestaña **Projects**
2. Expande el proyecto
3. Deberías ver:
   - **Dependencies** → Debe mostrar `mysql-connector-j`
   - **Source Packages** → Debe mostrar todos los packages

---

## ⚙️ Configuración de Maven en NetBeans

Si NetBeans no reconoce Maven:

1. **Tools → Options → Java → Maven**
2. Verifica que Maven esté habilitado
3. NetBeans viene con Maven incluido, pero puedes configurar una ruta personalizada si lo necesitas

---

## 🐛 Solución de Problemas

### Error: "Cannot resolve dependency"
**Solución:**
- Click derecho en el proyecto → **Dependencies → Download Declared Dependencies**
- O: **Clean and Build Project**

### Error: "Driver not found"
**Solución:**
- Verifica que en **Dependencies** aparezca `mysql-connector-j`
- Si no aparece, ejecuta: **Dependencies → Download Declared Dependencies**

### Error: "Project does not build"
**Solución:**
- Click derecho en el proyecto → **Clean and Build**
- Verifica que no haya errores de sintaxis en los archivos Java

### NetBeans no reconoce el proyecto como Maven
**Solución:**
- Cierra NetBeans
- Elimina la carpeta `.nbproject` si existe
- Abre NetBeans y vuelve a abrir el proyecto

---

## 📦 Dependencias Incluidas en pom.xml

- **MySQL Connector/J 8.0.33** - Driver para conectar con MySQL
  - Grupo: `com.mysql`
  - Artifact: `mysql-connector-j`
  - Versión: `8.0.33`

---

## ✅ Checklist Antes de Ejecutar

- [ ] Proyecto abierto en NetBeans
- [ ] Dependencias descargadas (verificar en Dependencies)
- [ ] MySQL corriendo
- [ ] Base de datos `ResidenciaSegura` creada
- [ ] Datos de ejemplo insertados
- [ ] Sin errores de compilación

---

## 🎯 Credenciales de Prueba

Después de ejecutar `datos_ejemplo.sql`:

- **Admin:** `admin@residencia.com` / `admin123`
- **Residente:** `maria@residencia.com` / `residente123`
- **Guardia:** `pedro@residencia.com` / `guardia123`

---

¡Listo para ejecutar! El driver de MySQL ya viene incluido gracias a Maven. 🎉

