# RunBlocker
 Esté é um Programa que bloqueia algum executavel ou algum programa instalado na sua maquina, sendo assim, utilizei algumas bibliotecas explicadas abaixo para a manipulação do mesmo e levantamento de dados, no qual é criado um arquivo na pasta: C:\Users\username\AppData\Local\Run Blocker\Teste.txt
Sendo que, este arquivo é basicamente uma lista de quase todos os programas .exe instalados na sua maquina. Realizei uma varredura no codigo que consegue diferenciar os .exe com as .dll, atraves desse arquivo, toda vez que o usuario inicia o programa ele é criado caso o usuario instale algum programa novo na maquima, e esse arquivo será usado também para leitura a fins de criar uma tabela na inferface grafica e o mesmo será escrito conforme o usuario queira adicionar algum arquivo executavel (.exe) que não esteja na lista. 
Além dessa tabela que mostra os arquivos instalados, terá uma nova tabela que ainda irei implementar na qual mostra os arquivos .exe bloqueados pelo usuario.
 
 Utilizei a biblioteca JNI (Java Native Interface) para a manipulacao no editor de registro do Windows, logo, esse programa não funciona no linux.
 Futuramente se eu tiver tempo eu vou implementar para linux para um maior conhecimento, caso alguém saiba bloquear a execução de programas no linux me mostre pois estou disposto a aprender já que sou iniciante, obrigado! :)

 Além desse codigo ser quase 100% feito em java, eu também criei um arquivo .bat para mim realizar a criação de algumas chaves de registro do jogo League of Legends, que por algum motivo não existem essas chaves nos diretorios:
  HKLM\SOFTWARE\Microsoft\Windows\CurrentVersion\App Paths\
Logo, ao executar o arquivo .bat ele cria uma chave do LeagueClient.exe passando como parametros o path do local do executavel e com isso o meu programa consegue ler e identificar automaticamente o local onde o .exe do League of legends foi instalado.

Conquanto, aproveitando que falei do arquivo bat, esse é um dos motivos principais de eu criar esse programa rsrs, eu ultimamente estou tendo alguns problemas para focar nos estudos e o jogo League Of Legends está ocupando muito meu tempo, sendo assim, resolvi criar essa iniciativa com a intenção de obter mais conhecimento e fazer parte de um dos meus primeiros projetos feitos em JAVA.

Em breve soltarei uma release de testes...

