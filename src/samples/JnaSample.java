package samples;

import java.util.Map;
import com.sun.jna.platform.win32.Advapi32Util;
import static com.sun.jna.platform.win32.WinReg.HKEY_CURRENT_USER;
import static com.sun.jna.platform.win32.WinReg.HKEY_LOCAL_MACHINE;



public class JnaSample {



    // https://github.com/twall/jna#readme

    //  you need 2 jars : jna-3.5.1.jar and platform-3.5.1.jar



    public static void main(String[] args) {

    	//Conseguir o valor de algum dado
        System.out.println(Advapi32Util.registryGetStringValue

                (HKEY_CURRENT_USER,

                 "Software\\Microsoft\\Internet Explorer\\Main", "Search Page")

        );

        //Conseguir o diretorio de um lugar
        System.out.println(Advapi32Util.registryGetStringValue

                (HKEY_LOCAL_MACHINE,

                 "Software\\Microsoft\\Windows\\CurrentVersion\\App Paths\\iTunes.exe",

                 ""));

        //Pegar o valor contido nos dados path que no caso é um diretorio
        System.out.println(Advapi32Util.registryGetStringValue

                (HKEY_LOCAL_MACHINE,

                 "Software\\Microsoft\\Windows\\CurrentVersion\\App Paths\\iTunes.exe",

                 "Path"));

        //Pegar os dados contidos em Frequency
        System.out.println(Advapi32Util.registryGetStringValue

                (HKEY_LOCAL_MACHINE,

                 "Software\\Wow6432Node\\Javasoft\\Java Update\\Policy\\jucheck",

                "Frequency"));

        //Faz uma lista das chaves contidas no diretorio policies
        String [] keys = Advapi32Util.registryGetKeys

                (HKEY_CURRENT_USER,

                 "SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Policies"

                 );

        for (String key : keys) {

            System.out.println(key);

        }

        //Faz um mapeamento das chaves com seus respectivos valores
        Map <String, Object>values = Advapi32Util.registryGetValues

        (HKEY_CURRENT_USER,

         "Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings"

         );

        System.out.println(values);
        //Printa somente o nome do dado ao inves de printar os seus valores
        for (Object value : values.values()) {

            System.out.println(value);

        }



        System.out.println(Advapi32Util.registryKeyExists(HKEY_CURRENT_USER, "Software\\RealHowTo")); //Verifica se existe

        Advapi32Util.registryCreateKey(HKEY_CURRENT_USER, "Software\\RealHowTo"); //Cria uma chave RealHowTo

        System.out.println(Advapi32Util.registryKeyExists(HKEY_CURRENT_USER, "Software\\RealHowTo")); //Verifica se existe denovo

        Advapi32Util.registrySetStringValue

            (HKEY_CURRENT_USER, "Software\\RealHowTo", "url", "http://www.rgagnon.com"); //cria um dado chamado url e atribui um link como valor

        System.out.println(Advapi32Util.registryValueExists(HKEY_CURRENT_USER, "Software\\RealHowTo", "url")); //Verifica se o dado url existe naquele diretorio

        System.out.println(Advapi32Util.registryValueExists(HKEY_CURRENT_USER, "Software\\RealHowTo", "foo")); //Verifica se o dado foo existe
        
        Advapi32Util.registryDeleteKey(HKEY_CURRENT_USER, "Software\\RealHowTo", "url"); //Deleta o dado especifico contido na chave Real HowTo
        
        Advapi32Util.registryDeleteKey(HKEY_CURRENT_USER, "Software\\RealHowTo"); //Deleta a chave Real HowTo
    }



}
