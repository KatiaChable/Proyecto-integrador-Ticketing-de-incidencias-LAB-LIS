# Sistema de Ticketing de Incidencias - LAB LIS  
Proyecto Final - Diseño y Programación Orientada a Objetos  
Ingeniería de Software – Universidad Veracruzana, FCA Coatzacoalcos  

---

## Descripción del Proyecto

El Laboratorio de Cómputo LAB-LIS cuenta con múltiples equipos utilizados diariamente por estudiantes y personal académico. Como parte de su operación, es necesario registrar, dar seguimiento y resolver fallas técnicas en los equipos de manera organizada.

Este proyecto implementa un **Sistema de Ticketing por Consola**, desarrollado en **Java**, aplicando los principios de **Programación Orientada a Objetos (POO)**.

El sistema permite:

- Registrar incidencias en equipos del laboratorio  
- Asignar prioridades y tipos (Hardware, Software, Red)  
- Gestionar el ciclo de vida completo de las incidencias  
- Asignar técnicos responsables  
- Consultar y filtrar incidencias  
- Generar reportes automáticos  
- Contar incidencias por estado, tipo y técnico asignado  

La aplicación está pensada para funcionar desde consola, siendo sencilla, clara y completamente funcional.


## Funcionalidades Principales

### ✔ Registrar nuevas incidencias  
El usuario ingresa sus datos, selecciona un equipo y describe la falla.

### ✔ Asignar técnico a incidencia  
Se selecciona un técnico en función de su especialidad.

### ✔ Actualizar el estado de una incidencia  
Permite cambiar entre:
- Nueva  
- Abierta  
- En Proceso  
- Resuelta  
- Cerrada  

### ✔ Filtrar incidencias por estado  
Muestra únicamente las incidencias que cumplan el filtro.

### ✔ Generar reportes  
Incluye:
- Conteo por estado  
- Conteo por tipo  
- Carga de trabajo por técnico  
- Incidencias pendientes por prioridad  

---

## 📦 Estructura del Proyecto

src/
├── App.java
├── TicketManager.java
├── ReportGenerator.java
├── Incidencia.java
├── Usuario.java
├── Tecnico.java
├── EquipoComputo.java
└── Enums.java

## Ejecución del Proyecto

GESTIÓN DE INCIDENCIAS
-------------------------
1. Registrar Nueva Incidencia
2. Asignar Técnico a Incidencia
3. Actualizar Estado de Incidencia
4. Filtrar Incidencias por Estado
5. Generar Reportes
0. Salir

## Pruebas por Consola

En el word se incluyen capturas de pantalla donde se evidencia el cumplimiento de cada Historia de Usuario, incluyendo:

HU - 01 Registrar incidencia

HU - 02 Asignar técnico

HU - 03 Actualizar estado

HU - 04 Filtrar incidencias

HU - 05 Reporte de incidencias

HU - 06 Reporte de carga de trabajo por técnico

Cada captura está asociada a su HU correspondiente.

## Notas finales
Este proyecto fue desarrollado como parte del Proyecto Integrador de la materia Programación Orientada a Objetos, aplicando principios fundamentales como:

Encapsulamiento
Responsabilidad única
Diagrama de clases UML
Listas de objetos
Relaciones entre clases
Enumeraciones


Katia Chablé
Estudiante de Ingeniería de Software
Universidad Veracruzana – FCA Coatzacoalcos