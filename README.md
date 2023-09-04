# Sistema DEVSU
Este es un sistema de gestión que permite a los clientes administrar sus cuentas y realizar movimientos financieros.

## Tabla de Contenidos

- [Requisitos](#requisitos)
- [Instalación](#instalación)
- [API](#api)
- [Licencia](#licencia)

## Requisitos

- Java 11
- Maven
- Base de datos H2 (se configura automáticamente)

## Instalación

1. Clona este repositorio:

```bash
git clone https://github.com/ericyepez/devsu.git
cd sistema-gestion-bancaria 
```

2. Compila  y ejecuta la pplicacion:
```bash
mvn spring-boot:run
```

## API

La API proporciona varias rutas para gestionar clientes, cuentas y movimientos:

    /clientes: Gestión de clientes.
    /cuentas: Gestión de cuentas.
    /movimientos: Gestión de movimientos.
    /reportes: Generación de informes de cuentas.
