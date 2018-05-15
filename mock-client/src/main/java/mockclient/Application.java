package mockclient;

import org.apache.commons.cli.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Caozheng on 2018/5/14.
 */
@SpringBootApplication
@EnableEurekaClient
public class Application {

    private static ConfigurableApplicationContext ctx;

    public static void main(String[] args) throws Exception{

        List<String> argList = getArg(args);

        String[] a = new String[argList.size()];

        Thread t = new Thread(()->{
            readLine();
        });
        t.setDaemon(true);
        t.start();

        ctx = SpringApplication.run(Application.class, argList.toArray(a));

    }

    private static void readLine(){
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            while(true){
                String cmd = reader.readLine();
                if (cmd.trim().equals("exit")){
                    ctx.close();

                    return;
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private static List<String> getArg(String[] args){
        Options options = new Options();
        options.addOption(address()).addOption(name()).addOption(hostname()).addOption(help());
        List<String> argList = new ArrayList<>();

        try {
            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(options, args);

            if(cmd.hasOption('h')){
                HelpFormatter hf = new HelpFormatter();
                hf.printHelp("eureka mock client", "", options, "");

                System.exit(0);
            }

            if(!cmd.hasOption('a')){
                System.out.println("eureka servers required");
                System.exit(1);
            }

            if(!cmd.hasOption('n')){
                System.out.println("service name required");
                System.exit(1);
            }


            argList.add("--eureka.client.service-url.defaultZone=" + cmd.getOptionValue('a'));
            argList.add("--eureka.instance.hostname=" +
                    (cmd.hasOption("hostname")?cmd.getOptionValue("hostname"):"${HOSTNAME}"));
            argList.add("--spring.application.name="+cmd.getOptionValue('n'));


        }catch (ParseException pe){
            System.out.println(pe.getMessage());
        }

        return argList;
    }

    private static Option address(){
        return Option.builder("a")
                .longOpt("address")
                .desc("eureka servers list")
                .argName("eureka servers")
                .hasArg()
                .build();
    }

    private static Option name(){
        return Option.builder("n")
                .longOpt("name")
                .desc("service name")
                .argName("service name")
                .hasArg()

                .build();

    }

    public static Option hostname(){
        return Option.builder()
                .longOpt("hostname")
                .desc("host name")
                .argName("host name")
                .hasArg()
                .build();
    }

    private static Option help(){
        return Option.builder("h")
                .longOpt("help")
                .desc("help").build();
    }


}
