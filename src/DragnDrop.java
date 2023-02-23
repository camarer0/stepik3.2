import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class DragnDrop {
    static JFrame frame = new JFrame();//создаем форму
    static JPanel panel = new JPanel();//создаем панель
    static JLabel label = new JLabel("Возьми меня");//создаем объект
    static Point startPoint;//координаты точки, за которую ухватились мышкой
    static boolean move=false;

    public static void start(MouseEvent e){//метод захвата объекта мышкой
        if (e.getButton()==3)//если кнопка правая
        {
            startPoint = e.getPoint();//сохраняем координаты точки, за которую ухватились (это координаты именно JLabel, относительные)
            move=true;//метка о начале движения по правой кнопке
        }
    }

    public static void stop(MouseEvent e){//метод окончания перемещения
        move=false;
    }

    public static void move(MouseEvent e){//метод перемещения
        if (move) {//если было начало движения
            Point p = SwingUtilities.convertPoint(label, e.getPoint(), panel);//координаты JLabel приводим к координатам панели
            label.setLocation(p.x - startPoint.x, p.y - startPoint.y);//перемещаем, не забыв вычесть координаты точки, за которую ухватились.
        }
    }

    public static void main(String[] args) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//способ выхода из формы
        frame.setTitle("Drag and drop");//заголовок формы
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();//определяем разрешение монитора
        int width = 400, height = 400;//задаем размер окна
        frame.setBounds(dim.width / 2 - width / 2, dim.height / 2 - height / 2, width, height);//выставляем размеры окна
        frame.add(panel);//добавляем панель на форму
        panel.add(label);//добавляем метку на панель
        label.addMouseListener(new MouseAdapter() {//добавляем слушателя мыши на метку
            public void mousePressed(MouseEvent e) {//нажатие кнопки
                start(e);
            }
        });
        label.addMouseListener(new MouseAdapter() {//добавляем слушателя мыши на метку
            public void mouseReleased(MouseEvent e) {//нажатие кнопки
                stop(e);
            }
        });
        label.addMouseMotionListener(new MouseMotionAdapter() {//добавляем слушателя мыши на метку
            public void mouseDragged(MouseEvent e) {//движение мыши
                move(e);
            }
        });
        frame.setVisible(true);//делаем форму видимой
    }
}
