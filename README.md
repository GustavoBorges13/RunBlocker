# Run Blocker
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/GustavoBorges13/RunBlocker/blob/main/LICENSE) 
```diff
- Em desenvolvimento!
+ Atualização 1.5
# First SOLO Project
```
# Sobre o projeto
 Esté é um Programa que bloqueia algum executavel ou algum programa instalado na sua maquina, sendo assim, ao bloquear certos programas ou arquivos, o sistema do windows vai bloquear a execução caso ele esteja na lista de programas bloqueados. 
 Neste projeto utilizei algumas bibliotecas explicadas abaixo para a manipulação do mesmo e levantamento de dados, no qual é criado 2 arquivos na pasta: C:\Users\[username]\AppData\Local\Run Blocker, onde dentro desta pasta o primeiro arquivo que é criado se trata de uma lista de quase todos os programas instalados na maquina do usuário, e o outro arquivo é o banco de dados, onde será salvo os arquivos que o usuario bloqueou. 
 Como realizei a varredura? Realizei uma varredura no editor de registro onde os valores contidos nas pastas HKLM\SOFTWARE\Microsoft\Windows\CurrentVersion\App Paths\ são salvas em ArrayLists e no mesmo existe algumas linhas de codigo capaz de diferenciar os .exe com as .dll, no qual toda vez que o usuario iniciar o programa ele cria um arquivo novo contendo a lista dos programas instalados na máquina com o nome de ProgramsInstalled.txt, pois pode ser que no futuro o usuario instale programas novos e para isso seria necessário criar linhas de codigo capaz de atualizar e buscar os dados automaticamente. Ainda falando sobre o arquivo, o outro arquivo criado é uma lista de programas bloqueados contidos na pasta HKEY_CURRENT_USER\SOFTWARE\Microsoft\Windows\CurrentVersion\Policies\Explorer\DisallowRun, onde essas chaves serão usadas para criar um ArrayList com a função de mostrar os arquivos bloqueados pelo usuário com o nome de ProgramsBlocked.txt. De modo geral, essa manipulação de arquivo é responsável pela gravação e leitura com a intenção de criar uma tabela na inferface grafica onde o mesmo poderá ser manipulado pelo usuário conforme a necessidade de adicionar algum arquivo executavel (.exe) que não esteja na lista. 
 
 Utilizei a biblioteca JNI (Java Native Interface) para mudar a aplicação em um modo legado para que eu consiga realizar a manipulacao no editor de registro do Windows de forma indireta, logo, esse programa não funciona no linux.
 Futuramente se eu tiver tempo eu vou implementar para linux para um maior conhecimento, caso alguém saiba bloquear a execução de programas no linux me mostre pois estou disposto a aprender já que sou iniciante, obrigado! :)

 Além desse codigo ser quase 100% feito em java, eu também criei um arquivo .bat para mim realizar a criação de algumas chaves de registro do jogo League of Legends, que por algum motivo não existem essas chaves nos diretorios:
  HKLM\SOFTWARE\Microsoft\Windows\CurrentVersion\App Paths\
Logo, ao executar o arquivo .bat ele cria uma chave do LeagueClient.exe passando como parametros o path do local do executavel e com isso o meu programa consegue ler e identificar automaticamente o local onde o .exe do League of legends foi instalado.

Conquanto, aproveitando que falei do arquivo bat, esse é um dos motivos principais de eu criar esse programa rsrs, eu ultimamente estou tendo alguns problemas para focar nos estudos e o jogo League Of Legends está ocupando muito meu tempo, sendo assim, resolvi criar essa iniciativa com a intenção de obter mais conhecimento e fazer parte de um dos meus primeiros projetos feitos em JAVA.

Em breve soltarei uma release de testes...

