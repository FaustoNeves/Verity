# Verity Desafio

Endpoints utilizados
https://api.github.com
![base_endpoint](https://github.com/FaustoNeves/Verity/assets/66192808/802cfb2c-b685-4d98-8602-a6e859014ce9)

https://api.github.com/users
![users](https://github.com/FaustoNeves/Verity/assets/66192808/5ba2514c-e66b-4eb8-bbdd-76793629b0b1)

https://api.github.com/users/{login}
![mojombo](https://github.com/FaustoNeves/Verity/assets/66192808/eabc7d76-e203-4918-838e-6e1db99b6929)

https://api.github.com/users/{login}/repos
![repos](https://github.com/FaustoNeves/Verity/assets/66192808/b77cc564-c91d-4001-9215-184348a1f0c5)
![repos_2](https://github.com/FaustoNeves/Verity/assets/66192808/95ab9d3d-4c4e-4614-ac78-6ccb4fae7eee)

Versão utilizada do Android Studio: Android Studio Giraffe | 2022.3.1
Android Gradle Plugin Version 8.1.0
Gradle Version 8.0
JDK: jbr-17 JetBrains Runtime version 17.0.6

Emuladores utilizados para os testes:
Pixel 4 XL api lvl 31 (Android 12)
Pixel 4 XL api lvl 30 (Android 11)

Arquitetura do aplicativo

![architecture_tree](https://github.com/FaustoNeves/Verity/assets/66192808/dac3627b-bfdc-4bc9-9282-a1a76ed03de6)

Desabilite os efeitos de animação para rodar os testes, rode no terminal do Android Studio:
-
```adb shell settings put global window_animation_scale 0 ```
-
```adb shell settings put global transition_animation_scale 0```
-
```adb shell settings put global animator_duration_scale 0```
-

Caso o comando não funcione, é preciso ativar o modo de desenvolvedor e remover os efeitos de animação manualmente.

Para executar os testes, inicie o emulador e, em seguida, insira no terminal:
-
```./gradlew connectedAndroidTest```

Após a execução dos testes, procure no terminal pelo link do report:
-
![link report](https://github.com/FaustoNeves/Verity/assets/66192808/ae88c297-7945-4c0d-8db4-a75c854dac13)

Esse link abrirá para a página mostrando o relatório dos testes:
-
![teste1](https://github.com/FaustoNeves/Verity/assets/66192808/cc8e7fe9-4cfe-4f79-ace4-d88193234080)
-
![teste2](https://github.com/FaustoNeves/Verity/assets/66192808/b4508368-9903-478b-8446-f3ed496a405d)
-
![testes3](https://github.com/FaustoNeves/Verity/assets/66192808/1fd2274d-5321-4f37-95bf-23589dcb5eda)

Obrigado, pessoal!


