package com.xinguangnet.tucao.protubuf.gen;/**
 * Created by hanyong on 2017/8/10.
 */

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hanyong
 * @Date 2017/8/10
 */

@Component
public class Business {
    Logger logger = LoggerFactory.getLogger(Business.class);

    public void doBusiness(String tokeFile, String orderNoFile, String destinationFile) {

        LineIterator orderNoLines = null;
        try {
            List<String> tokeList = FileUtils.readLines(Paths.get(tokeFile).toFile(), StandardCharsets.UTF_8);
            orderNoLines = FileUtils.lineIterator(Paths.get(orderNoFile).toFile()); // should be closed after used.
            File destFile = Paths.get(destinationFile).toFile();


            int initialCapacityOfList = 100;
            List<String> protoLines = new ArrayList<>(initialCapacityOfList);
            while (orderNoLines.hasNext()) {

                String nextOrderNo = orderNoLines.nextLine();

                String newLine = genNewLine(tokeList.get(RandomUtils.nextInt(0, tokeList.size())), nextOrderNo);
                protoLines.add(newLine);

                if (protoLines.size() == initialCapacityOfList) {
                    writeToFile(destFile, protoLines);
                    protoLines.clear();
                }
            }
            writeToFile(destFile, protoLines);

        } catch (IOException e) {
            logger.error("error: {}", e.getMessage(), e);
        } finally {
            orderNoLines.close();
        }

    }

    private void writeToFile(File file, List<String> lines) throws IOException {
        if (lines.size() > 0) {
            FileUtils.writeLines(file, lines, true);
            logger.info("write {} lines to destination.", lines.size());
        }

    }


    private String genNewLine(String randomToken, String orderNo) {
        // TODO
        StringBuffer line = new StringBuffer();
        line.append(randomToken);
        line.append("-----");
        line.append(orderNo);


        return line.toString();

    }
}
