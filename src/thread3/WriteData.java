package thread3;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * 管道通信
 */
public class WriteData {
    /**
     * 向输出管道写数据
     */
    public void writeMethod(PipedOutputStream out) {
        System.out.println("write : ");
        for (int i = 0; i < 300; i++) {
            String outData = "" + (i + 1);
            try {
                out.write(outData.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(outData);
        }
        System.out.println();
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ReadData {
    public void readMethod(PipedInputStream in) {
        System.out.println("read : ");
        byte[] bytes = new byte[20];
        int readLength = 0;
        try {
            readLength = in.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (readLength != -1) {
            String newData = new String(bytes, 0, readLength);
            System.out.println(newData);
            try {
                readLength = in.read(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ThreadWrite extends Thread {
    private WriteData writeData;
    private PipedOutputStream outputStream;

    public ThreadWrite(WriteData writeData, PipedOutputStream outputStream) {
        this.writeData = writeData;
        this.outputStream = outputStream;
    }

    @Override
    public void run() {
        writeData.writeMethod(outputStream);
    }
}

class ThreadRead extends Thread {
    private ReadData readData;
    private PipedInputStream inputStream;

    public ThreadRead(ReadData readData, PipedInputStream inputStream) {
        this.readData = readData;
        this.inputStream = inputStream;
    }

    @Override
    public void run() {
        readData.readMethod(inputStream);
    }
}

class Run {
    public static void main(String[] args) {
        try {
            WriteData writeData = new WriteData();
            ReadData readData = new ReadData();

            PipedInputStream inputStream = new PipedInputStream();
            PipedOutputStream outputStream = new PipedOutputStream();
            outputStream.connect(inputStream);

            ThreadRead threadRead = new ThreadRead(readData, inputStream);
            threadRead.start();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            ThreadWrite threadWrite = new ThreadWrite(writeData, outputStream);
            threadWrite.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}