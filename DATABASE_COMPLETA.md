# 🗄️ Base de Datos Residencia Segura - Script Completo

Este archivo contiene el script completo de creación de la base de datos y todos los datos de prueba con campos completos.

---

## 📋 Script de Creación de la Base de Datos

```sql
/* =====================================================
   BASE DE DATOS: ResidenciaSegura
   ===================================================== */

CREATE DATABASE IF NOT EXISTS ResidenciaSegura
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE ResidenciaSegura;

/* =====================================================
   TABLA: usuarios
   ===================================================== */

CREATE TABLE usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(150) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL,
    rol ENUM('admin', 'residente', 'guardia') NOT NULL,
    departamento VARCHAR(50) NOT NULL,
    telefono VARCHAR(20),
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

/* =====================================================
   TABLA: pagos
   ===================================================== */

CREATE TABLE pagos (
    id_pago INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    tipo_servicio ENUM('alicuota', 'agua', 'luz', 'internet', 'mantenimiento', 'otro') NOT NULL,
    monto DECIMAL(10,2) NOT NULL,
    fecha_pago DATE NOT NULL,
    estado ENUM('pendiente', 'pagado', 'rechazado') NOT NULL,
    metodo_pago ENUM('efectivo', 'transferencia', 'tarjeta') NOT NULL,
    comprobante VARCHAR(255),
    observaciones TEXT,

    CONSTRAINT fk_pagos_usuario
        FOREIGN KEY (id_usuario)
        REFERENCES usuarios(id_usuario)
        ON DELETE CASCADE
);

/* =====================================================
   TABLA: reportes
   ===================================================== */

CREATE TABLE reportes (
    id_reporte INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    tipo ENUM('seguridad', 'mantenimiento', 'ruido', 'emergencia', 'otro') NOT NULL,
    descripcion TEXT NOT NULL,
    ubicacion VARCHAR(150) NOT NULL,
    fecha_reporte TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    estado ENUM('pendiente', 'en_proceso', 'resuelto', 'cancelado') NOT NULL,
    prioridad ENUM('baja', 'media', 'alta', 'critica') NOT NULL,
    id_guardia_atendio INT,
    fecha_resolucion TIMESTAMP NULL,

    CONSTRAINT fk_reportes_usuario
        FOREIGN KEY (id_usuario)
        REFERENCES usuarios(id_usuario)
        ON DELETE CASCADE,

    CONSTRAINT fk_reportes_guardia
        FOREIGN KEY (id_guardia_atendio)
        REFERENCES usuarios(id_usuario)
        ON DELETE SET NULL
);

/* =====================================================
   TABLA: avisos
   ===================================================== */

CREATE TABLE avisos (
    id_aviso INT AUTO_INCREMENT PRIMARY KEY,
    id_administrador INT NOT NULL,
    titulo VARCHAR(150) NOT NULL,
    mensaje TEXT NOT NULL,
    tipo ENUM('informativo', 'alerta', 'mantenimiento', 'emergencia') NOT NULL,
    fecha_publicacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    activo BOOLEAN DEFAULT TRUE,

    CONSTRAINT fk_avisos_admin
        FOREIGN KEY (id_administrador)
        REFERENCES usuarios(id_usuario)
        ON DELETE CASCADE
);
```

---

## 👥 Usuarios de Prueba

### 🔴 Administrador

```sql
INSERT INTO usuarios (nombre, correo, contrasena, rol, departamento, telefono) VALUES
('Juan Pérez', 'admin@residencia.com', 'admin123', 'admin', 'Administración', '555-0001');
```

**Datos del Usuario:**
- **ID:** 1
- **Nombre:** Juan Pérez
- **Correo:** admin@residencia.com
- **Contraseña:** admin123
- **Rol:** admin (Administrador)
- **Departamento:** Administración
- **Teléfono:** 555-0001

---

### 🟢 Residentes

```sql
INSERT INTO usuarios (nombre, correo, contrasena, rol, departamento, telefono) VALUES
('María García', 'maria@residencia.com', 'residente123', 'residente', '101', '555-0002'),
('Carlos López', 'carlos@residencia.com', 'residente123', 'residente', '202', '555-0003'),
('Ana Rodríguez', 'ana@residencia.com', 'residente123', 'residente', '303', '555-0005'),
('Luis Martínez', 'luis@residencia.com', 'residente123', 'residente', '404', '555-0006'),
('Carmen Sánchez', 'carmen@residencia.com', 'residente123', 'residente', '105', '555-0007');
```

**Residente 1 - María García:**
- **ID:** 2
- **Nombre:** María García
- **Correo:** maria@residencia.com
- **Contraseña:** residente123
- **Rol:** residente
- **Departamento:** 101
- **Teléfono:** 555-0002

**Residente 2 - Carlos López:**
- **ID:** 3
- **Nombre:** Carlos López
- **Correo:** carlos@residencia.com
- **Contraseña:** residente123
- **Rol:** residente
- **Departamento:** 202
- **Teléfono:** 555-0003

**Residente 3 - Ana Rodríguez:**
- **ID:** 5
- **Nombre:** Ana Rodríguez
- **Correo:** ana@residencia.com
- **Contraseña:** residente123
- **Rol:** residente
- **Departamento:** 303
- **Teléfono:** 555-0005

**Residente 4 - Luis Martínez:**
- **ID:** 6
- **Nombre:** Luis Martínez
- **Correo:** luis@residencia.com
- **Contraseña:** residente123
- **Rol:** residente
- **Departamento:** 404
- **Teléfono:** 555-0006

**Residente 5 - Carmen Sánchez:**
- **ID:** 7
- **Nombre:** Carmen Sánchez
- **Correo:** carmen@residencia.com
- **Contraseña:** residente123
- **Rol:** residente
- **Departamento:** 105
- **Teléfono:** 555-0007

---

### 🟡 Guardias

```sql
INSERT INTO usuarios (nombre, correo, contrasena, rol, departamento, telefono) VALUES
('Pedro Martínez', 'pedro@residencia.com', 'guardia123', 'guardia', 'Portería', '555-0004'),
('Roberto Fernández', 'roberto@residencia.com', 'guardia123', 'guardia', 'Portería', '555-0008');
```

**Guardia 1 - Pedro Martínez:**
- **ID:** 4
- **Nombre:** Pedro Martínez
- **Correo:** pedro@residencia.com
- **Contraseña:** guardia123
- **Rol:** guardia
- **Departamento:** Portería
- **Teléfono:** 555-0004

**Guardia 2 - Roberto Fernández:**
- **ID:** 8
- **Nombre:** Roberto Fernández
- **Correo:** roberto@residencia.com
- **Contraseña:** guardia123
- **Rol:** guardia
- **Departamento:** Portería
- **Teléfono:** 555-0008

---

## 💰 Pagos de Ejemplo

```sql
INSERT INTO pagos (id_usuario, tipo_servicio, monto, fecha_pago, estado, metodo_pago, comprobante, observaciones) VALUES
-- Pagos de María García (ID: 2)
(2, 'alicuota', 150.00, '2024-01-15', 'pagado', 'transferencia', 'TRF-2024-001', 'Pago mensual de alícuota - Enero 2024'),
(2, 'agua', 25.50, '2024-01-20', 'pagado', 'efectivo', 'EFE-2024-001', 'Pago de servicio de agua'),
(2, 'luz', 45.75, '2024-02-05', 'pagado', 'tarjeta', 'TAR-2024-001', 'Pago de servicio eléctrico'),

-- Pagos de Carlos López (ID: 3)
(3, 'alicuota', 150.00, '2024-01-15', 'pendiente', 'transferencia', NULL, 'Pendiente de pago - Enero 2024'),
(3, 'luz', 45.75, '2024-01-18', 'pagado', 'tarjeta', 'TAR-2024-002', 'Pago de servicio eléctrico'),
(3, 'internet', 35.00, '2024-01-25', 'pagado', 'transferencia', 'TRF-2024-002', 'Pago de servicio de internet'),

-- Pagos de Ana Rodríguez (ID: 5)
(5, 'alicuota', 150.00, '2024-01-15', 'pagado', 'transferencia', 'TRF-2024-003', 'Pago mensual de alícuota'),
(5, 'agua', 28.00, '2024-01-22', 'pagado', 'efectivo', 'EFE-2024-002', 'Pago de servicio de agua'),
(5, 'mantenimiento', 50.00, '2024-02-01', 'pendiente', 'transferencia', NULL, 'Pago de mantenimiento común'),

-- Pagos de Luis Martínez (ID: 6)
(6, 'alicuota', 150.00, '2024-01-15', 'pagado', 'transferencia', 'TRF-2024-004', 'Pago mensual de alícuota'),
(6, 'luz', 52.30, '2024-01-20', 'pagado', 'tarjeta', 'TAR-2024-003', 'Pago de servicio eléctrico'),

-- Pagos de Carmen Sánchez (ID: 7)
(7, 'alicuota', 150.00, '2024-01-15', 'rechazado', 'transferencia', 'TRF-2024-005', 'Transferencia rechazada - Fondos insuficientes'),
(7, 'agua', 30.00, '2024-01-18', 'pagado', 'efectivo', 'EFE-2024-003', 'Pago de servicio de agua');
```

---

## 📢 Reportes de Ejemplo

```sql
INSERT INTO reportes (id_usuario, tipo, descripcion, ubicacion, estado, prioridad, id_guardia_atendio, fecha_resolucion) VALUES
-- Reportes de María García (ID: 2)
(2, 'mantenimiento', 'Fuga de agua en el baño del departamento 101. El grifo del lavabo gotea constantemente.', 'Departamento 101 - Baño', 'pendiente', 'alta', NULL, NULL),
(2, 'mantenimiento', 'Puerta del departamento no cierra correctamente. La cerradura está desalineada.', 'Departamento 101 - Entrada principal', 'resuelto', 'baja', 4, '2024-01-20 14:30:00'),

-- Reportes de Carlos López (ID: 3)
(3, 'seguridad', 'Ruido excesivo en el departamento 202 durante la noche. Música alta después de las 11 PM.', 'Departamento 202', 'en_proceso', 'media', 4, NULL),
(3, 'mantenimiento', 'Lámpara del pasillo no funciona. Necesita cambio de bombilla.', 'Pasillo - Segundo piso', 'pendiente', 'baja', NULL, NULL),

-- Reportes de Ana Rodríguez (ID: 5)
(5, 'emergencia', 'Cortocircuito en el área común del tercer piso. Chispas visibles en el panel eléctrico.', 'Área común - 3er piso', 'pendiente', 'critica', NULL, NULL),
(5, 'ruido', 'Vecinos del departamento 304 hacen ruido excesivo durante el día.', 'Departamento 304', 'resuelto', 'media', 4, '2024-01-25 10:15:00'),

-- Reportes de Luis Martínez (ID: 6)
(6, 'mantenimiento', 'Ascensor hace ruidos extraños al subir al cuarto piso.', 'Ascensor - Edificio principal', 'en_proceso', 'alta', 8, NULL),
(6, 'seguridad', 'Persona sospechosa merodeando en el estacionamiento.', 'Estacionamiento - Nivel 1', 'resuelto', 'alta', 4, '2024-01-22 18:45:00'),

-- Reportes de Carmen Sánchez (ID: 7)
(7, 'mantenimiento', 'Ventana del departamento 105 no cierra bien. Entra aire y lluvia.', 'Departamento 105 - Sala', 'pendiente', 'media', NULL, NULL);
```

---

## 📋 Avisos de Ejemplo

```sql
INSERT INTO avisos (id_administrador, titulo, mensaje, tipo, activo) VALUES
(1, 'Mantenimiento Programado del Elevador', 
 'Se realizará mantenimiento preventivo del elevador el próximo sábado 27 de enero de 8:00 AM a 12:00 PM. Durante este tiempo el servicio estará suspendido. Agradecemos su comprensión.', 
 'mantenimiento', TRUE),

(1, 'Reunión de Vecinos', 
 'Se convoca a todos los residentes a la reunión de vecinos el próximo viernes 26 de enero a las 7:00 PM en el salón comunal. Se tratarán temas importantes de la administración.', 
 'informativo', TRUE),

(1, 'Corte de Agua Programado', 
 'Por trabajos de mantenimiento en la red de agua potable, habrá corte de servicio el día 25 de enero de 9:00 AM a 3:00 PM. Se recomienda almacenar agua con anticipación.', 
 'alerta', TRUE),

(1, 'Nuevas Medidas de Seguridad', 
 'A partir del próximo mes se implementarán nuevas medidas de seguridad en el edificio. Se instalará sistema de cámaras y se reforzará el control de acceso. Favor revisar el nuevo reglamento en la administración.', 
 'informativo', TRUE),

(1, 'Emergencia: Corte de Luz', 
 'ATENCIÓN: Se reporta corte de energía eléctrica en el sector. El personal técnico está trabajando para resolverlo. Se estima restablecimiento en 2 horas.', 
 'emergencia', TRUE),

(1, 'Limpieza de Tanques de Agua', 
 'Se realizará limpieza y mantenimiento de los tanques de agua el próximo lunes 29 de enero. El servicio de agua estará interrumpido de 6:00 AM a 2:00 PM.', 
 'mantenimiento', TRUE);
```

---

## 📊 Resumen de Datos Insertados

### Usuarios
- **1 Administrador** (Juan Pérez)
- **5 Residentes** (María, Carlos, Ana, Luis, Carmen)
- **2 Guardias** (Pedro, Roberto)
- **Total: 8 usuarios**

### Pagos
- **12 pagos** con diferentes estados (pagado, pendiente, rechazado)
- Diferentes tipos de servicios (alicuota, agua, luz, internet, mantenimiento)
- Diferentes métodos de pago (transferencia, efectivo, tarjeta)

### Reportes
- **10 reportes** con diferentes tipos y prioridades
- Estados variados (pendiente, en_proceso, resuelto)
- Algunos atendidos por guardias

### Avisos
- **6 avisos** de diferentes tipos (informativo, alerta, mantenimiento, emergencia)
- Todos activos para visualización

---

## 🚀 Cómo Ejecutar los Scripts

### Opción 1: Desde MySQL Workbench o phpMyAdmin

1. Copia y pega el script de creación de tablas
2. Ejecuta el script completo
3. Luego ejecuta los INSERT de usuarios, pagos, reportes y avisos

### Opción 2: Desde Línea de Comandos MySQL

```bash
mysql -u root -p < script_completo.sql
```

### Opción 3: Ejecutar por Partes

1. Primero ejecuta la creación de tablas
2. Luego ejecuta los INSERT de usuarios
3. Después los INSERT de pagos
4. Luego los INSERT de reportes
5. Finalmente los INSERT de avisos

---

## 🔐 Credenciales de Acceso al Sistema

### Administrador
- **Correo:** admin@residencia.com
- **Contraseña:** admin123

### Residentes
- **Correo:** maria@residencia.com | **Contraseña:** residente123
- **Correo:** carlos@residencia.com | **Contraseña:** residente123
- **Correo:** ana@residencia.com | **Contraseña:** residente123
- **Correo:** luis@residencia.com | **Contraseña:** residente123
- **Correo:** carmen@residencia.com | **Contraseña:** residente123

### Guardias
- **Correo:** pedro@residencia.com | **Contraseña:** guardia123
- **Correo:** roberto@residencia.com | **Contraseña:** guardia123

---

## ✅ Verificación Post-Instalación

Ejecuta estas consultas para verificar que todo se insertó correctamente:

```sql
-- Verificar usuarios
SELECT COUNT(*) as total_usuarios FROM usuarios;
SELECT * FROM usuarios;

-- Verificar pagos
SELECT COUNT(*) as total_pagos FROM pagos;
SELECT * FROM pagos;

-- Verificar reportes
SELECT COUNT(*) as total_reportes FROM reportes;
SELECT * FROM reportes;

-- Verificar avisos
SELECT COUNT(*) as total_avisos FROM avisos;
SELECT * FROM avisos;
```

---

¡Base de datos lista para usar! 🎉

