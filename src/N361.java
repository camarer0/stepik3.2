import javax.swing.*;
import java.awt.*;

public class N361{
    public static void main(String[] args) {
        JFrame frame = new JFrame();//создаем форму
        frame.setLayout(new GridBagLayout());//задаем менеджер расположения объектов
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//способ выхода из формы
        frame.setTitle("Первая надпись");//заголовок формы
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();//определяем разрешение монитора
        int width=800, height=600;//задаем размер окна
        frame.setBounds(dim.width / 2 - width / 2, dim.height / 2 - height / 2, width, height);//выставляем размеры окна
        JPanel panel = new JPanel ();//создаем панель
        JLabel label = new JLabel("Моя первая надпись!");
        label.setFont(new Font("Verdana", Font.ITALIC, 50));
        frame.add(panel);//добавляем панель на форму
        panel.add(label);//добавляем надпись
        frame.setVisible(true);//делаем форму видимой
    }
}