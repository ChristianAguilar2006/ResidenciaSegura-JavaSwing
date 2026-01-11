-- Script para eliminar el campo metodo_pago de la tabla pagos
-- Ejecutar este script en MySQL antes de ejecutar la aplicación

USE ResidenciaSegura;

ALTER TABLE pagos DROP COLUMN metodo_pago;

