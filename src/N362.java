import javax.swing.*;
import java.awt.*;

public class N362 {
    public static void main(String[] args) {
        JFrame frame = new JFrame();//создаем форму
        frame.setLayout(new BorderLayout());//задаем менеджер расположения объектов, чтобы потом добавит 2 панели
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//способ выхода из формы
        frame.setTitle("Первая надпись");//заголовок формы
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();//определяем разрешение монитора
        int width = 1200, height = 400;//задаем размер окна
        frame.setBounds(dim.width / 2 - width / 2, dim.height / 2 - height / 2, width, height);//выставляем размеры окна
        JPanel panel1 = new JPanel(), panel2 = new JPanel();//создаем панель
        JSlider slider = new JSlider(5, 100, 50);//создаем слайдер
        JLabel label = new JLabel("Моя первая надпись!");//создаем надпись
        label.setFont(new Font("Verdana", Font.ITALIC, 50));//устанавливаем ей шрифт
        frame.add(panel1);//добавляем первую панель на форму
        panel1.setLayout(new GridBagLayout());//устанавливаем разметку для панели, чтобы надпись поместилась в центр
        //добавляем слушателя на слайдер
        slider.addChangeListener(e -> label.setFont(new Font("Verdana", Font.ITALIC, slider.getValue())));
        frame.add(panel2, BorderLayout.SOUTH);//добавляем вторую панель на форму внизу
        panel1.add(label);//добавляем надпись на первую панель (по центру)
        panel2.add(slider);//добавляем слайдер на вторую панель (внизу)
        frame.setVisible(true);//делаем форму видимой
    }
}
