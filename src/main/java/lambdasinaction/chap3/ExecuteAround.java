package lambdasinaction.chap3;

import java.io.*;

public class ExecuteAround {
  
  public static final String FILE_PATH = "D:\\Denis_Work\\github_qub2qub\\j8api\\src\\main\\java\\lambdasinaction\\chap3\\data.txt";
  
  public static void main(String... args) throws IOException {
//    InputStream res = ExecuteAround.class.getResourceAsStream("/lambdasinaction/chap3/data.txt");
//    InputStreamReader is = new InputStreamReader(res);
//    FileReader fr = new FileReader("D:\\Denis_Work\\github_qub2qub\\j8api\\src\\main\\java\\lambdasinaction\\chap3\\data.txt");
    System.out.println( processFileLimited());
    System.out.println("-------------------------");
    // т.е. лямбдой создаём новый класс процессора с заданным поведением
    System.out.println( processFile(BufferedReader::readLine));
    System.out.println( processFile((BufferedReader b) -> "["+b.readLine() +"\n" +  b.readLine()+"]"));
  }
  
  public static String processFileLimited() throws IOException {
    try (BufferedReader br = new BufferedReader(
      new FileReader(FILE_PATH)
    )) {
      return br.readLine();
    }
  }
  // ТУТ ОПИСАН ExecuteAround КОД, ВНУТРИ КОТОРОГО ВЫЗЫВАЕТСЯ ПОВЕДЕНИЕ
  // lets you pass different lambdas to work with a BufferedReader object
  public static String processFile(BufferedReaderProcessor p) throws IOException {
    try (BufferedReader br = new BufferedReader( new FileReader(FILE_PATH) )) {
      return p.process(br);
    }
  }
}
