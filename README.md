# Verity Desafio

Endpoints utilizados
https://api.github.com
![base_endpoint](https://github.com/FaustoNeves/Verity/assets/66192808/995d123f-7f15-459b-85cd-120c538ecdbe)

https://api.github.com/users
![users](https://github.com/FaustoNeves/Verity/assets/66192808/aac3f264-c92e-40ab-8987-4f4267aa2f17)

https://api.github.com/users/{login}
![mojombo](https://github.com/FaustoNeves/Verity/assets/66192808/87d14a93-1b4e-4375-a5a3-ae2f875e1263)

https://api.github.com/users/{login}/repos
![repos](https://github.com/FaustoNeves/Verity/assets/66192808/164b7a0e-4528-4b9a-8219-73f95ee50c46)
![repos_2](https://github.com/FaustoNeves/Verity/assets/66192808/8c07d8eb-afcc-4fc8-b609-346424c2cad2)

Versão utilizada do Android Studio: Android Studio Giraffe | 2022.3.1
Android Gradle Plugin Version 8.1.0
Gradle Version 8.0
JDK: jbr-17 JetBrains Runtime version 17.0.6

Emuladores utilizados para os testes:
Pixel 4 XL api lvl 31 (Android 12)
Pixel 4 XL api lvl 30 (Android 11)

Arquitetura do aplicativo

![architecture_tree](https://github.com/FaustoNeves/Verity/assets/66192808/058393cb-50c3-4e1f-8f1c-620749370152)


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
![link report](https://github.com/FaustoNeves/Verity/assets/66192808/f1220b14-05ab-40d4-a330-611df7027e80)


Esse link abrirá para a página mostrando o relatório dos testes:
-
![teste1](https://github.com/FaustoNeves/Verity/assets/66192808/7c18e153-1fe1-418e-b4bf-584776168eb7)

-
![teste2](https://github.com/FaustoNeves/Verity/assets/66192808/aed66b95-96b2-4c83-b859-746cc70f1b7f)

-
![testes3](https://github.com/FaustoNeves/Verity/assets/66192808/0dcf3437-5919-49d2-8656-eb93a6a1e101)


Obrigado, pessoal!


