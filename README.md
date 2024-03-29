# Run Blocker
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/GustavoBorges13/RunBlocker/blob/main/LICENSE) 
[![Latest Stable Version](https://img.shields.io/badge/version-v1.6.0-blue)](https://github.com/GustavoBorges13/RunBlocker/releases)
[![Build Status](https://app.travis-ci.com/GustavoBorges13/RunBlocker.svg?branch=main)](https://app.travis-ci.com/GustavoBorges13/RunBlocker)
![GitHub last commit](https://img.shields.io/github/last-commit/GustavoBorges13/RunBlocker)

```diff
- Finalizado!
+ Autor: Gustavo Borges Peres da Silva
# First SOLO Project
```
# Sobre o projeto
 Esté é um Programa que bloqueia algum executavel ou algum programa instalado na sua maquina, sendo assim, ao bloquear certos programas ou arquivos, o sistema do windows vai bloquear a execução caso ele esteja na lista de programas bloqueados. 
 
 Neste projeto utilizei algumas bibliotecas explicadas abaixo para a manipulação do mesmo e levantamento de dados, no qual é criado 3 arquivos na pasta de origem do executável, no qual dentro desta chamada DATA, o primeiro arquivo que é criado se trata de uma lista de quase todos os programas instalados na maquina do usuário, o segundo arquivo se trata de um banco de dados onde será salvo os arquivos que o usuario bloqueou e o terceiro arquivo é uma lista dos programas que o usuário adicionou de forma externa, ou seja, ele adicionou um arquivo ou um executável atráves da opção de adicionar um programa .exe. 
 
 Como realizei a varredura? Realizei uma varredura no editor de registro onde os valores contidos nas pastas HKLM\SOFTWARE\Microsoft\Windows\CurrentVersion\App Paths\ são salvas em ArrayLists e no mesmo existe algumas linhas de codigo capaz de diferenciar os .exe com as .dll, no qual toda vez que o usuario iniciar o programa ele cria um arquivo novo contendo a lista dos programas instalados na máquina com o nome de ProgramsInstalled.txt, pois pode ser que no futuro o usuario instale programas novos e para isso seria necessário criar linhas de codigo capaz de atualizar e buscar os dados automaticamente. Ainda falando sobre o arquivo, o outro arquivo criado é uma lista de programas bloqueados contidos na pasta HKEY_CURRENT_USER\SOFTWARE\Microsoft\Windows\CurrentVersion\Policies\Explorer\DisallowRun, onde essas chaves serão usadas para criar um ArrayList com a função de mostrar os arquivos bloqueados pelo usuário com o nome de ProgramsBlocked.txt. De modo geral, essa manipulação de arquivo é responsável pela gravação e leitura com a intenção de criar uma tabela na inferface grafica onde o mesmo poderá ser manipulado pelo usuário conforme a necessidade de adicionar algum arquivo executavel (.exe) que não esteja na lista. 
 
 Utilizei a biblioteca JNA (Java Native Access) é uma biblioteca desenvolvida pela comunidade que fornece aos programas Java acesso fácil a bibliotecas compartilhadas nativas sem utilizar a Interface Nativa Java. O projeto da JNA visa fornecer acesso nativo de uma forma natural com um mínimo de esforço. Ao contrário da JNI, não é necessária nenhuma placa de caldeira ou código de cola gerada. Usei ela basicamente para mudar a aplicação em um modo legado para que eu consiga realizar a manipulacao no editor de registro do Windows de forma indireta, logo, esse programa não funciona no linux e também precisa ser executado com permissões administrativas.
 
 Futuramente se eu tiver tempo eu vou implementar para linux para um maior conhecimento, caso alguém saiba bloquear a execução de programas no linux me mostre pois estou disposto a aprender já que sou iniciante, obrigado! :)

 Além desse codigo ser quase 100% feito em java, eu também criei um arquivo .bat para mim realizar a criação de algumas chaves de registro do jogo League of Legends, que por algum motivo não existem essas chaves nos diretorios:
  HKLM\SOFTWARE\Microsoft\Windows\CurrentVersion\App Paths\
  
Logo, ao executar o arquivo .bat ele cria uma chave do LeagueClient.exe passando como parametros o path do local do executavel e com isso o meu programa consegue ler e identificar automaticamente o local onde o .exe do League of legends foi instalado.

Conquanto, aproveitando que falei do arquivo bat, esse é um dos motivos principais de eu criar esse programa rsrs, eu ultimamente estou tendo alguns problemas para focar nos estudos e o jogo League Of Legends está ocupando muito meu tempo, sendo assim, resolvi criar essa iniciativa com a intenção de obter mais conhecimento e fazer parte de um dos meus primeiros projetos feitos em JAVA.


# Requirimentos
Necessário ter Windows 10 64-bit ou Windows 10 32-bit.
O usuário deve ter permissções administrativas para que o programa possa manipular registros do windows.

# Instruções
Caso prefira apenas rodar o programa, baixe o executavel correspondente à sua versão disponível no link abaixo: 
```sh
https://github.com/GustavoBorges13/RunBlocker/releases/tag/v1.6.0
```

Após isso basta extrair utilizando alguma ferramenta de descompactação.

E por seguinte, basta executar o programa, no qual eu configurei para pedir ao usuario que deia permissões de administração para ler e gravar registros.
Do lado esquerdo sáo os programas instalados e adicionados, e o lado direito sáo os programas bloqueados.

Se o programa não funcionar corretamente, certifique-se de estar como administrador e também clicar com o botão direito do mouse em cima da aplicação e executar como administrador pois ainda não sei como configurar corretamente o launch pra salvar as preferencias de compatibilidade pro usuario não precisar fazer isto todas as vezes... e caso o programa acuse como vírus o codigo, fique tranquilo, este codigo foi feito por um estudante portanto o windows não reconhece que o autor é alguem "famoso" ou que possui alguma licença pra elaborar softwares, e além disso o meu codigo mexe com manipulação de arquivos de registro então o vírus que o windows reconhece é nada mais nada menos que um Trojan:Script/Wacatac.H!ml, cujo, de acordo com a makeuseof, se trata de um Wacatac. B! ml é classificado como um Trojan pelo Windows Defender porque entra nos sistemas operativos Windows ao enganar os utilizadores na execução de um ficheiro de aspecto legítimo. No momento em que infecta o seu sistema, coloca-o em risco de roubo de identidade, infecção de dados, e perda financeira.
Contudo, meu codigo sim ele entra no editor de registro do windows e pega as informações dos programas instalados na maquina do usuario e joga todas essas informações em um bloco de notas dentro da pasta DATA localizada no mesmo diretorio do arquivo executavel. Além disso, se o usuario bloquear algum programa ele cria outro arquivo e joga os dados neles e também realiza a escritura das informações do programa que o usuario quis bloquear, para desfazer a operação uma vez realizada antes basta o processo inverso de bloquear (selecione o programa e remova e depois aplique). Caso deseja remover manualmente para evitar problemas ou se o programa deu algum bug por favor siga esse procedimento:

Digite Win+R, digite regedit e pressione enter.
Após isto aparecerá a tela do editor de registro do windows, logo basta navegar neste diretorio ou dar um ctrl+c e ctrl+v para chegar mais rapido utilizando este endereço -> HKEY_CURRENT_USER\SOFTWARE\Microsoft\Windows\CurrentVersion\Policies\Explorer\DisallowRun
Após chegar aqui, basta excluir a chave EXPLORER e isso irá tirar todos os programas que bloqueamos e chaves que criamos no editor de registro.

Agora se você veio para testar o League of Legends, primeiro vai ter que executar o script para configurar as chaves de registro do jogo que náo é configurada corretamente pelos desenvolvedores do jogo. Sendo assim, faça o download do source code e navegue até a pasta Scripts e execute-o.
```sh
RunBlocker/src/scripts/LoL ADD path.bat
```
