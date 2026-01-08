-- =====================================================
-- DATOS DE EJEMPLO PARA RESIDENCIA SEGURA
-- =====================================================

USE ResidenciaSegura;

-- Insertar usuarios de ejemplo
INSERT INTO usuarios (nombre, correo, contrasena, rol, departamento, telefono) VALUES
('Juan Pérez', 'admin@residencia.com', 'admin123', 'admin', 'Administración', '555-0001'),
('María García', 'maria@residencia.com', 'residente123', 'residente', '101', '555-0002'),
('Carlos López', 'carlos@residencia.com', 'residente123', 'residente', '202', '555-0003'),
('Pedro Martínez', 'pedro@residencia.com', 'guardia123', 'guardia', 'Portería', '555-0004'),
('Ana Rodríguez', 'ana@residencia.com', 'residente123', 'residente', '303', '555-0005');

-- Insertar pagos de ejemplo
INSERT INTO pagos (id_usuario, tipo_servicio, monto, fecha_pago, estado, metodo_pago, observaciones) VALUES
(2, 'alicuota', 150.00, '2024-01-15', 'pagado', 'transferencia', 'Pago mensual de alícuota'),
(2, 'agua', 25.50, '2024-01-20', 'pagado', 'efectivo', 'Pago de servicio de agua'),
(3, 'alicuota', 150.00, '2024-01-15', 'pendiente', 'transferencia', 'Pendiente de pago'),
(3, 'luz', 45.75, '2024-01-18', 'pagado', 'tarjeta', 'Pago de servicio eléctrico'),
(5, 'alicuota', 150.00, '2024-01-15', 'pagado', 'transferencia', 'Pago mensual de alícuota');

-- Insertar reportes de ejemplo
INSERT INTO reportes (id_usuario, tipo, descripcion, ubicacion, estado, prioridad) VALUES
(2, 'mantenimiento', 'Fuga de agua en el baño del departamento 101', 'Departamento 101', 'pendiente', 'alta'),
(3, 'seguridad', 'Ruido excesivo en el departamento 202 durante la noche', 'Departamento 202', 'en_proceso', 'media'),
(5, 'emergencia', 'Cortocircuito en el área común del tercer piso', 'Área común - 3er piso', 'pendiente', 'critica'),
(2, 'mantenimiento', 'Puerta del departamento no cierra correctamente', 'Departamento 101', 'resuelto', 'baja');

-- Insertar avisos de ejemplo
INSERT INTO avisos (id_administrador, titulo, mensaje, tipo, activo) VALUES
(1, 'Mantenimiento Programado', 'Se realizará mantenimiento del elevador el próximo sábado de 8:00 AM a 12:00 PM', 'mantenimiento', TRUE),
(1, 'Reunión de Vecinos', 'Se convoca a reunión de vecinos el próximo viernes a las 7:00 PM en el salón comunal', 'informativo', TRUE),
(1, 'Corte de Agua', 'Por trabajos de mantenimiento, habrá corte de agua el día 25 de enero de 9:00 AM a 3:00 PM', 'alerta', TRUE),
(1, 'Nuevas Medidas de Seguridad', 'A partir del próximo mes se implementarán nuevas medidas de seguridad. Favor revisar el reglamento.', 'informativo', TRUE);

