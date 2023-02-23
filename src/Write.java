import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class Write {
    static JFrame frame = new JFrame();//создаем форму
    static JLabel l;//объект с текстом
    static File file = new File("keylogger.txt");//объявляем файл для записи
    static FileWriter writer;//объявляем writer для записи в файл

    static public void print(KeyEvent e) throws IOException {//метод движения по клавиатуре
        if (e.getKeyCode()!=KeyEvent.VK_ESCAPE) {
            l.setText(KeyEvent.getKeyText(e.getKeyCode()));//показываем нажатие в метке
            writer.write(KeyEvent.getKeyText(e.getKeyCode()));//записываем в файл
        }
        else {//по  эскейпу прекращаем запись и выводим информацию, затем можно вновь продолжать ввод
            writer.flush();//сохраняем введенное
            Stream<String> log = Files.lines(Paths.get("keylogger.txt"));//создаем стрим для чтения данных из файла
            String temp = Arrays.deepToString(log.toArray());//из стрима делаем массив, массив переводим в строку
            l.setText(temp.substring(1,temp.length()-1));//выводим в метку все введенное содержимое
            log.close();//закрываем стрим, т.к. потом нам его возможно вновь надо будет открыть
            PrintWriter tempWriter = new PrintWriter(file);//очищаем файл, теперь он готов к вводу новых данных
            tempWriter.print("");
            tempWriter.close();
        }
    }

    public static void main(String[] args) throws IOException {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//способ выхода из формы
        frame.setTitle("Запись клавиш");//заголовок формы
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();//определяем разрешение монитора
        int width=600, height=100;//задаем размер окна
        frame.setBounds(dim.width / 2 - width / 2, dim.height / 2 - height / 2, width, height);//выставляем размеры окна
        JPanel panel = new JPanel (new FlowLayout(FlowLayout.CENTER));//создаем панель, чтобы ей отлавливать события клавиатуры, ставим ее слева
        panel.setFocusable(true);//делаем у панели возможность принимать фокус, иначе она не сможет отловить события клавиатуры
        l = new JLabel();//создаем объект
        panel.add(l, BorderLayout.CENTER);//добавляем на панель по центру
        frame.add(panel);//добавляем панель на форму
        file.createNewFile();//создаем файл для записи
        writer = new FileWriter(file);//создаем врайтер для файла
        panel.addKeyListener(new KeyAdapter() {//добавляем слушателя на панель
            public void keyReleased(KeyEvent e) {
                try {
                    print(e);//метод записи
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        frame.setVisible(true);//делаем форму видимой
    }
}