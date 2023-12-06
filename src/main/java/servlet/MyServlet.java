package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("cp1251");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        ArrayList<String> altxt = new ArrayList<>();
        String findfile = request.getParameter("p1");
        System.out.println(findfile);
        Path root = Paths.get("C:\\Users\\user\\Desktop\\Laba8Part1\\");
        List<Path> result = null;
        FileReader fr = null;
        try (Stream<Path> pathStream = Files.find(root,
                Integer.MAX_VALUE,
                (p, basicFileAttributes) ->
                        p.getFileName().toString().equalsIgnoreCase(findfile + ".txt"))
        ) {
            result = pathStream.collect(Collectors.toList());
            if (result.size() == 0) {
                System.out.println("File not found: " + findfile);
                fr = new FileReader("C:\\Users\\user\\Desktop\\Laba8Part1\\error.txt");
            } else {
                Path filePath = result.get(0);
                System.out.println("Reading file: " + filePath);
                try (Stream<String> lines = Files.lines(filePath)) {
                    altxt = lines.collect(Collectors.toCollection(ArrayList::new));
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (fr != null) {
                fr.close();
            }
        }

        String size = request.getParameter("p2");
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Text Redactor Server</title>");
        out.println("</head>");
        out.println("<h1 align='center'><font face='Calibri'><b>Текст из файла</b></font><br></h1>");
        out.println("<div style='font-size:" + size + "px;' align='center'>");
        if (findfile.equals("поэзия")) {
            out.println("<font face='Brush Script MT, Brush Script Std, cursive'>");
        } else if (findfile.equals("проза") || findfile.equals("наука")) {
            out.println("<font face='FreeMono, monospace'>");
        } else if (findfile.equals("фантастика")) {
            out.println("<font face='Marker Felt, fantasy'>");
        }

        for (String line : altxt) {
            out.println(line + "<br/>");
        }

        out.println("</font>");
        out.println("</div>");
        out.println("</html>");
        out.close();
    }
}
