package servlet;

import java.io.IOException;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;

@WebServlet("/sort")
public class SortServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reqArray = req.getParameter("array");

        if (StringUtils.EMPTY.equals(reqArray)) {
            req.setAttribute("error", "field is empty");
            req.getRequestDispatcher("/").forward(req, resp);
            return;
        }

        int[] array = Arrays.stream(reqArray.split(",")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(array);

        req.setAttribute("array", array);
        req.getRequestDispatcher("jsp/sort.jsp").forward(req, resp);
    }
}
