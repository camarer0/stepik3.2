import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class N369{
    private static int curQuestion=0, allQuestion, allTrue;//номер текущего вопроса, всего вопросов, всего правильных ответов
    private static String curTrue;//правильный ответ
    private static Document doc;
    private static JLabel label;
    private static JTextArea textArea;
    private static final JFrame frame = new JFrame();//создаем форму
    private static final JPanel panel1 = new JPanel (new GridBagLayout()), panel2 = new JPanel (new GridBagLayout()), panel3 = new JPanel (new GridBagLayout());//создаем панели
    private static final GridBagConstraints gbc = new GridBagConstraints();//задаем параметры менеджера выравнивания

    private static void nextQuestion() {
        curQuestion++;//+1 к номеру вопроса
        label.setText("Вопрос "+curQuestion+" из "+allQuestion);//обновляем номер вопроса

        Color greenColor = new Color(0, 205, 102);//зеленый
        Color yellowColor = new Color(240, 230, 140);//желтый
        Color orangeColor = new Color(255, 165, 0);//оранжевый
        Color redColor = new Color(205, 92, 92);//красный

        Node question = doc.getElementsByTagName("name").item(curQuestion-1);//ищем вопрос
        textArea.setText(question.getTextContent());//пишем его в форму
        curTrue=question.getAttributes().getNamedItem("true").getTextContent();//ищем правильный ответ
        Element answersElement = (Element) doc.getElementsByTagName("answers").item(curQuestion - 1);//берем нужный список ответов (головной элемент)
        NodeList answers = answersElement.getElementsByTagName("answer");//и забираем сами ответы

        var buttons = new ArrayList<JButton>();//создаем список для хранения создаваемых в цикле кнопок
        Font fontButton = new Font("TimesRoman", Font.PLAIN, 20);//указываем шрифт для кнопок

        panel3.removeAll();//удаляем все с панели

        for (int i = 0; i < answers.getLength(); i++) {//пробегаемся по перечню ответов
            buttons.add(new JButton(String.valueOf(i)));//создаем кнопки и добавляем их в список
            JButton newButton = buttons.get(i);//вытаскиваем название объекта кнопки
            newButton.setFont(fontButton);//меняем шрифт
            newButton.setText(answers.item(i).getTextContent());//пишем ответ в кнопку
            panel3.add(newButton, gbc);//добавляем на вторую панель

            if (String.valueOf(i+1).equals(curTrue))//если именно это правильный ответ
                curTrue = answers.item(i).getTextContent();//номер ответа превращаем в текст

            newButton.addActionListener(e -> {//добавляем слушателя на кнопку
                if (newButton.getText().equals(curTrue)) {//если ответ правильный
                    allTrue++;//увеличиваем счетчик правильных ответов
                    newButton.setBackground(greenColor);//подсвечиваем зеленым
                } else
                    newButton.setBackground(redColor);//иначе красным
                for (JButton b:buttons)
                    b.setEnabled(false);//отключаем кнопки пока действует задержка
                frame.setVisible(true);
                Timer timer = new Timer();//делаем задержку
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if (curQuestion < allQuestion) nextQuestion();//если вопросы еще есть, то задаем следующий
                        else {//выводим результат
                            int percent = allTrue*100/allQuestion;//считаем правильные ответы в процентах
                            panel2.removeAll();
                            frame.remove(panel1);
                            frame.remove(panel3);
                            if (percent>74) panel2.setBackground(greenColor);//делаем подсветку в зависимости от результата
                            else if (percent<25) panel2.setBackground(redColor);
                            else if (percent<50) panel2.setBackground(orangeColor);
                            else panel2.setBackground(yellowColor);
                            JLabel labelResult = new JLabel("Правильных ответов: " + allTrue+" из "+allQuestion+" или "+percent+"%", SwingConstants.CENTER);//добавляем метку с результатов
                            labelResult.setFont(fontButton);
                            panel2.add(labelResult, gbc);
                            frame.setVisible(true);
                        }
                    }
                }, 1000);
            });
        }
        frame.pack();
        frame.setVisible(true);
    }

    static void openXML() throws ParserConfigurationException, IOException, SAXException {//метод открытия XML
        File file = new File("victorina.xml");//открываем файл
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();//получаем документ
        doc = documentBuilder.parse(file);//парсим его
        allQuestion=doc.getElementsByTagName("question").getLength();//вычисляем количество вопросов
    }

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        frame.setLayout(new BorderLayout());//задаем менеджер расположения объектов, чтобы потом добавить 2 панели
        frame.setResizable(false);//отключаем возможность изменения размеров
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//способ выхода из формы
        frame.setTitle("Викторина");//заголовок формы
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();//определяем разрешение монитора
        int width=1000, height=500;//задаем размер окна
        frame.setBounds(dim.width / 2 - width / 2, dim.height / 2 - height / 2, width, height);//выставляем размеры окна

        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        panel1.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));//устанавливаем границы панелям
        panel2.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        panel3.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        label = new JLabel();//добавляем метку с номером вопроса
        panel1.add(label,gbc);

        textArea = new JTextArea();//создаем текстареа
        textArea.setEnabled(false);//отключаем у него ввод
        textArea.setLineWrap(true);//делаем возможность переноса
        textArea.setColumns(30);//устанавливаем число столбцов, чтоб не сжимался
        textArea.setFont(new Font("TimesRoman", Font.BOLD, 20));//меняем шрифт
        textArea.setDisabledTextColor(Color.BLACK);//меняем цвет шрифта отключенного текстареа, чтобы его было лучше видно
        panel2.add(textArea,gbc);//добавляем на панель

        frame.add(panel1,BorderLayout.NORTH);//добавляем панели на форму
        frame.add(panel2,BorderLayout.CENTER);
        frame.add(panel3,BorderLayout.SOUTH);

        openXML();//открываем XML
        nextQuestion();//следующий вопрос
    }
}
