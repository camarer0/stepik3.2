import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Name {
    public static void main(String[] args) {
        JFrame frame = new JFrame();//создаем форму
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//способ выхода из формы
        frame.setTitle("Два диалоговых окна");//заголовок формы
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();//определяем разрешение монитора
        int width=400, height=200;//задаем размер окна
        frame.setBounds(dim.width / 2 - width / 2, dim.height / 2 - height / 2, width, height);//выставляем размеры окна
        JPanel panel = new JPanel ();//создаем панель
        panel.setFocusable(true);//делаем у панели возможность принимать фокус, иначе она не сможет отловить события клавиатуры
        frame.add(panel);//добавляем панель на форму
        panel.addKeyListener(new KeyAdapter() {//добавляем слушателя на панель
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_SPACE) //если нажат пробел
                {
                    JOptionPane pane = new JOptionPane();//создаем первый диалог
                    String result = pane.showInputDialog(panel, "Как вас зовут?", "Вопрос", JOptionPane.QUESTION_MESSAGE);//выводим вопрос
                    new JOptionPane().showMessageDialog (pane, result,"Ответ", JOptionPane.INFORMATION_MESSAGE);//выводим ответ
                }
            }
        });
        frame.setVisible(true);//делаем форму видимой
    }
}