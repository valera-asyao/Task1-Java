import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class Task1{
    public static void main(String[] args) {
        try (FileOutputStream outputStream = new FileOutputStream("input.bin")) {
            for(int i = 0; i < 10; i++)
            {
                int value = (int) (Math.random() * 1000);
                outputStream.write(value >> 24); 
                outputStream.write(value >> 16); 
                outputStream.write(value >> 8); 
                outputStream.write(value); 
            }
            
            
        }       
        catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }

        try (FileInputStream inputStream = new FileInputStream("input.bin")){
            int num;
            while((num = inputStream.read()) != -1){
                System.out.print(num + "\t");
            }
        }
        catch(IOException e){
            e.printStackTrace();
            System.exit(0);
        }
    }
}