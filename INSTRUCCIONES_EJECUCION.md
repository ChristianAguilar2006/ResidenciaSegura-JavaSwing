# 🚀 Cómo Ejecutar el Proyecto Residencia Segura

## Opción 1: Desde NetBeans (RECOMENDADO) ⭐

### Pasos:

1. **Abre NetBeans**
   - Inicia Apache NetBeans IDE

2. **Abre el Proyecto**
   - File → Open Project
   - Selecciona la carpeta `ProyectoDarielAnudesk`
   - Click en "Open Project"

3. **Agrega el Driver de MySQL**
   - Click derecho en el proyecto → Properties
   - Libraries → Add JAR/Folder
   - Selecciona el archivo `mysql-connector-java-8.0.XX.jar`
   - Si no lo tienes, descárgalo de: https://dev.mysql.com/downloads/connector/j/

4. **Configura la Base de Datos**
   - Asegúrate de que MySQL esté corriendo
   - Ejecuta el script SQL para crear la base de datos
   - Ejecuta `datos_ejemplo.sql` para datos de prueba

5. **Ejecuta el Proyecto**
   - Click derecho en `VentanaLogin.java`
   - Selecciona "Run File"
   - O presiona `Shift + F6`

---

## Opción 2: Desde Línea de Comandos

### Requisitos Previos:
- ✅ Java JDK instalado y en el PATH
- ✅ Driver de MySQL descargado
- ✅ MySQL corriendo

### Pasos:

1. **Descarga el Driver de MySQL**
   - Ve a: https://dev.mysql.com/downloads/connector/j/
   - Descarga el archivo JAR
   - Colócalo en una carpeta `lib` dentro del proyecto

2. **Crea las Carpetas Necesarias**
   ```
   mkdir build
   mkdir lib
   ```

3. **Compila el Proyecto**
   ```bash
   javac -encoding UTF-8 -d build -cp "src;lib/mysql-connector-java-8.0.XX.jar" src/com/residenciasegura/**/*.java
   ```

4. **Ejecuta la Aplicación**
   ```bash
   java -cp "build;lib/mysql-connector-java-8.0.XX.jar" com.residenciasegura.vista.VentanaLogin
   ```

---

## Opción 3: Usar los Scripts Batch (Windows)

### Pasos:

1. **Descarga el Driver de MySQL**
   - Colócalo en la carpeta `lib` (crea la carpeta si no existe)

2. **Ejecuta COMPILAR.bat**
   - Doble click en `COMPILAR.bat`
   - Verifica que compile sin errores

3. **Ejecuta EJECUTAR.bat**
   - Doble click en `EJECUTAR.bat`
   - La aplicación debería iniciar

---

## ⚠️ Solución de Problemas

### Error: "Driver de MySQL no encontrado"
**Solución:** 
- Descarga el driver desde: https://dev.mysql.com/downloads/connector/j/
- Colócalo en la carpeta `lib` del proyecto
- En NetBeans: Agrégalo a las Libraries del proyecto

### Error: "No se puede conectar a MySQL"
**Solución:**
- Verifica que MySQL esté corriendo
- Verifica las credenciales en `ConexionDB.java`
- Verifica que la base de datos `ResidenciaSegura` exista

### Error: "Clase no encontrada"
**Solución:**
- Verifica que todos los archivos estén en las carpetas correctas
- Verifica que el classpath incluya todas las carpetas necesarias

### Error al Compilar en NetBeans
**Solución:**
- Clean and Build Project (clic derecho → Clean and Build)
- Verifica que no haya errores de sintaxis
- Verifica que todas las clases estén guardadas

---

## 📝 Credenciales de Prueba

Después de ejecutar `datos_ejemplo.sql`:

- **Admin:** 
  - Correo: `admin@residencia.com`
  - Contraseña: `admin123`

- **Residente:**
  - Correo: `maria@residencia.com`
  - Contraseña: `residente123`

- **Guardia:**
  - Correo: `pedro@residencia.com`
  - Contraseña: `guardia123`

---

## ✅ Checklist Antes de Ejecutar

- [ ] MySQL está corriendo
- [ ] Base de datos `ResidenciaSegura` creada
- [ ] Datos de ejemplo insertados
- [ ] Driver de MySQL descargado y agregado al proyecto
- [ ] Proyecto abierto en NetBeans
- [ ] Sin errores de compilación

---

¡Listo para ejecutar! 🎉

