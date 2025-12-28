# 📚 Login Responsive & Adaptative de una biblioteca
## 📖 Descripción
Aplicación Android desarrollada en **Kotlin con Jetpack Compose** que implementa un sistema de login/registro con diseño **responsive y adaptative** de una biblioteca. Proyecto para la asignatura DAM2: Desarrollo de interfazes.

## 📖 Características principales
### 📗 **Responsive Design**
- Se adapta automáticamente al tamaño de pantalla
- Los elementos escalan proporcionalmente
- Padding y tamaños dinámicos

### 📗 **Adaptative Layout**
- Diseños diferentes para orientación vertical/horizontal
- Para hacer más visuales los cambios en los diseños adaptative, el texto de los composables cambia
- Banner cambia de posición automáticamente
- Formularios se reorganizan según espacio disponible
- Tres layouts: compact, medium, expanded

### 📗 **Validaciones completas**
- Campos obligatorios
- Formato de email válido
- Fecha en formato dd/mm/yyyy
- Contraseñas coincidentes
- Términos y condiciones obligatorios
- Teléfono numérico y con 9 dígitos

### 📗 **Arquitectura MVVM**
- ViewModel con LiveData
- Separación clara de responsabilidades
- Navegación con Navigation Component

## 📖 Capturas de pantalla
### 📗 **Vista Vertical (Portrait)**
| Login | Registro | Confirmación |
|-------|----------|--------------|
| <img src="screenshots/login_portrait.jpeg" width="200"> | <img src="screenshots/signin_portrait.jpeg" width="200"> | <img src="screenshots/confirmation_portrait.jpeg" width="200"> |

### 📗 **Vista Horizontal (Landscape)**
| Login | Registro | Confirmación |
|-------|----------|--------------|
| <img src="screenshots/login_landscape.jpeg" width="400"> | <img src="screenshots/signin_landscape.jpeg" width="400"> | <img src="screenshots/confirmation_landscape.jpeg" width="400"> |

## 📖 Estructura del proyecto
```
com/example/loginresponsiveadaptative/
├── MainActivity.kt                   # Punto de entrada
├── model/
│   └── User.kt                       # Modelo de datos
├── nav/
│   └── Route.kt                      # Navegación
├── view/
│   ├── components/
│   │   ├── Banner.kt
│   │   └── ResponsiveLayout.kt
│   ├── LoginView.kt                  # Pantalla login
│   ├── SigninView.kt                 # Pantalla registro
│   └── ConfirmationView.kt           # Pantalla confirmación
├── ui/theme/
│   ├── Color.kt
│   ├── Theme.kt
│   └── Type.kt
└── viewmodel/
    └── AdaptativeViewModel.kt        # Lógica y validaciones
```

## 📖 Tecnologías utilizadas
- **Kotlin** - Lenguaje principal
- **Jetpack Compose** - UI declarativa
- **MVVM** - Patrón arquitectónico
- **Navigation Component** - Navegación
- **LiveData** - Estados reactivos
- **ViewModel** - Ciclo de vida

## 📖 Requisitos cumplidos
1. **Banner superior** con logo "BIBLIOTECA+" y descripción
2. **Formulario de registro** completo (8 campos)
3. **Validaciones** en tiempo real
4. **Diseño responsive** (compact, medium, expanded)
5. **Diseño adaptative** (vertical/horizontal)
6. **Navegación** entre pantallas
7. **Arquitectura MVVM** correcta
8. **Composable adecuados** para cada caso

## 📖 Cómo probar la aplicación
### 📗 Credenciales de prueba:
- **Email:** `jorgex1412@gmail.com`
- **Contraseña:** `12345`

### 📗 Pasos de prueba:
1. **Login** con las credenciales de prueba
2. **Registro** con datos nuevos (fecha: dd/mm/yyyy)
3. **Girar dispositivo** para ver cambios adaptative
4. **Probar validaciones** (datos incorrectos)

## 📖 Dependencias principales:
```kotlin
dependencies {
    implementation("androidx.compose.material3:material3")
    implementation("androidx.navigation:navigation-compose:2.7.5")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
}
```

## 👥 Autores
**Lucía Martínez** - Desarrollo de UI/UX y pantallas <br>
**Jordi Ros López** - Lógica de negocio y ViewModel <br>
Módulo 0488: Desarrollo de interfazes

## 📝 Licencia
Este proyecto es para fines educativos como parte del ciclo formativo de DAM2.
