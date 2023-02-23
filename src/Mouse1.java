import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Mouse1 {
    static JFrame frame = new JFrame();//создаем форму
    static JLayeredPane panel = new JLayeredPane();//создаем многослойную панель

    static public void add(MouseEvent e){//метод добавления объекта по клику
        if (e.getButton()==1){//если кнопка левая
            JLabel label = new JLabel("X:"+e.getX()+" Y:"+e.getY());
            label.setBounds(e.getX(),e.getY(),100,20);
            panel.add(label);
        }
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//способ выхода из формы
                frame.setTitle("Добавление мышкой");//заголовок формы
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();//определяем разрешение монитора
                int width = 400, height = 400;//задаем размер окна
                frame.setBounds(dim.width / 2 - width / 2, dim.height / 2 - height / 2, width, height);//выставляем размеры окна
                panel.setFocusable(true);//делаем у панели возможность принимать фокус, иначе она не сможет отловить события клавиатуры
                frame.add(panel);//добавляем панель на форму
                panel.addMouseListener(new MouseAdapter() {//добавляем слушателя мыши на панель
                    public void mouseClicked(MouseEvent e) {
                        add(e);
                    }
                });
                frame.setVisible(true);//делаем форму видимой
            }
        });
    }
}
