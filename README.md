# ⚔️ Duelos

Plugin de duelos 1v1 en español para servidores Paper/Spigot con soporte para GeyserMC (Bedrock).

Desarrollado por iiM02.

---

## 📋 Requisitos

- Paper/Spigot 1.21.x
- Java 21+

---

## 📦 Instalación

1. Descarga el `.jar` de la sección [Releases](https://github.com/iiM02/Duelos/releases)
2. Colócalo en la carpeta `plugins/` de tu servidor
3. Reinicia el servidor

---

## 💬 Comandos

| Comando | Descripción |
|---------|-------------|
| `/duelo <jugador>` | Reta a otro jugador a un duelo |
| `/aceptar` | Acepta un duelo pendiente |
| `/rechazar` | Rechaza un duelo pendiente |

---

## ⚙️ Funcionamiento

1. Jugador A usa `/duelo JugadorB`
2. JugadorB recibe la solicitud y tiene **30 segundos** para aceptar
3. JugadorB usa `/aceptar` o `/rechazar`
4. Si acepta, el duelo comienza donde están parados
5. El jugador que muere pierde
6. Si un jugador se desconecta durante el duelo, pierde automáticamente
7. Se anuncia el ganador a todo el servidor

---

## 🔨 Compilar desde código fuente

Requisitos: Java 21+, Maven 3.8+

```bash
cd Duelos
mvn clean package
```

El `.jar` queda en `target/Duelos-1.0.jar`.

---

## 📁 Estructura del proyecto

```
Duelos/
├── src/main/java/com/pluginhome/
│   ├── Duelos.java              ← Clase principal
│   ├── DueloManager.java        ← Gestión de duelos
│   ├── DueloCommand.java        ← /duelo
│   ├── AceptarCommand.java      ← /aceptar
│   ├── RechazarCommand.java     ← /rechazar
│   └── DueloListener.java       ← Detecta muerte y desconexión
├── src/main/resources/
│   └── plugin.yml
└── pom.xml
```