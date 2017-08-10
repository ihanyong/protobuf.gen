package com.xinguangnet.tucao.protubuf.gen;

import org.apache.commons.cli.Options;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Administrator on 2017/8/10.
 */
@SpringBootApplication
public class Application implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }


    @Override
    public void run(String... args) throws Exception {

    }


    private Options initOptions() {

        Options options = new Options();

        options.addOption("", false, "");
        options.addOption("", false, "");
        options.addOption("", false, "");
        options.addOption("", false, "");
        options.addOption("", false, "");
        options.addOption("", false, "");
        options.addOption("", false, "");




        return options;
    }
}

