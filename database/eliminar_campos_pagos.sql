-- Script para eliminar los campos comprobante y observaciones de la tabla pagos
-- Ejecuta este script en MySQL si ya tienes datos en la base de datos

USE ResidenciaSegura;

-- Eliminar el campo comprobante
ALTER TABLE pagos DROP COLUMN comprobante;

-- Eliminar el campo observaciones
ALTER TABLE pagos DROP COLUMN observaciones;

