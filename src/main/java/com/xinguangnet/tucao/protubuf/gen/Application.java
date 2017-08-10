package com.xinguangnet.tucao.protubuf.gen;

import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.Paths;

/**
 * Created by Administrator on 2017/8/10.
 */
@SpringBootApplication
public class Application implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    Logger logger = LoggerFactory.getLogger(Application.class);


    @Autowired
    private Business business;


    @Override
    public void run(String... args) throws Exception {

        Options options = initOptions();
        CommandLineParser parser = commandLineParser();

        CommandLine commandLine = parser.parse(options, args);
        if (null == args || args.length == 0 || commandLine.hasOption("h")) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("protobuf.gen", options);
            return;
        }


        String tokePath = commandLine.getOptionValue("t");
        String orderNumPath = commandLine.getOptionValue("o");
        String destPath = commandLine.getOptionValue("d");

        logger.info("");
        logger.info("* the tokens file is {}", tokePath);
        logger.info("* the orderNo file is {}", orderNumPath);
        logger.info("* the destination file is {}", destPath);

        if (assertNotEmpty("token file", tokePath) &&
                assertFileExist("token file", tokePath) &&
                assertNotEmpty("orderNo file", orderNumPath) &&
                assertFileExist("orderNo file", orderNumPath) &&
                assertNotEmpty("destination path", destPath)) {
            business.doBusiness(tokePath, orderNumPath, destPath);
        }



    }


    private boolean assertFileExist(String argName, String file) {
        boolean exist =  Paths.get(file).toFile().exists();
        if (!exist) {
            logger.info("error: {} no exists!", argName);
        }

        return exist;
    }
    private boolean assertNotEmpty(String argName, String value) {
        if (null == value || value.length() == 0) {
            logger.info("error: {} can not be empty!", argName);
            return false;
        }
        return true;
    }

    private CommandLineParser commandLineParser() {
        CommandLineParser parser = new DefaultParser();
        return parser;
    }

    private Options initOptions() {

        Options options = new Options();

        options.addOption("h", false, "show the usage");
        options.addOption("t", true, "the token file path");
        options.addOption("o", true, "the orderNo file path");
        options.addOption("d", true, "the destination path");

        return options;
    }
}

