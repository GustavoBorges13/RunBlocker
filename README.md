# RunBlocker
 Esté é um Programa que bloqueia algum executavel ou algum programa instalado na sua maquina, sendo assim, utilizei algumas bibliotecas explicadas abaixo para a manipulação do mesmo e levantamento de dados, no qual é criado um arquivo na pasta: C:\Users\username\AppData\Local\Run Blocker\Teste.txt
Sendo que, este arquivo é basicamente uma lista de quase todos os programas .exe instalados na sua maquina. Realizei uma varredura no codigo que consegue diferenciar os .exe com as .dll, atraves desse arquivo, toda vez que o usuario inicia o programa ele é criado caso o usuario instale algum programa novo na maquima, e esse arquivo será usado também para leitura a fins de criar uma tabela na inferface grafica e o mesmo será escrito conforme o usuario queira adicionar algum arquivo executavel (.exe) que não esteja na lista. 
Além dessa tabela que mostra os arquivos instalados, terá uma nova tabela que ainda irei implementar na qual mostra os arquivos .exe bloqueados pelo usuario.
 
 Utilizei a biblioteca JNI (Java Native Interface) para a manipulacao no editor de registro do Windows, logo, esse programa não funciona no linux.
 Futuramente se eu tiver tempo eu vou implementar para linux para um maior conhecimento, caso alguém saiba bloquear a execução de programas no linux me mostre pois estou disposto a aprender já que sou iniciante, obrigado! :)

Em breve soltarei uma release de testes...
