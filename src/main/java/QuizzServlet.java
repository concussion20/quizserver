import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

public class QuizzServlet extends javax.servlet.http.HttpServlet {

  protected void doPost(javax.servlet.http.HttpServletRequest request,
      javax.servlet.http.HttpServletResponse response)
      throws javax.servlet.ServletException, IOException {

  }

  protected void doGet(javax.servlet.http.HttpServletRequest request,
      javax.servlet.http.HttpServletResponse response)
      throws javax.servlet.ServletException, IOException {
    response.setContentType("text/plain");
    String urlPath = request.getPathInfo();

    // check we have a URL!
    if (urlPath == null || urlPath.isEmpty()) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.getWriter().write("missing paramterers");
      return;
    }

    String[] urlParts = urlPath.split("/");

    Integer number = isUrlValid(urlParts);
    if (number == null) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.getWriter().write("paramterers have wrong formats");
    } else {
      if (isPrime(number)) {
        response.setStatus(HttpServletResponse.SC_OK);
      } else {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      }
    }
  }

  private boolean isPrime(int number) {
    if (number == 1) {
      return false;
    }
    if (number == 2 || number == 3) {
      return true;
    }
    if (number % 2 == 0) {
      return false;
    }
    int sqrt = (int) Math.sqrt(number) + 1;
    for (int i = 3; i < sqrt; i += 2) {
      if (number % i == 0) {
        return false;
      }
    }
    return true;
  }

  private Integer isUrlValid(String[] urlPath) {
    if (urlPath.length != 2) {
      return null;
    }
    int number = Integer.parseInt(urlPath[1]);
    if (number < 1 || number > 10000) {
      return null;
    }
    return number;
  }
}
