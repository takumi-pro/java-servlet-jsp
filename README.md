# JAVA maven project
スッキリわかるサーブレット&JSPを参考にしつつ、サーブレットやJSPについて色々試すリポジトリ

## Manager Appの認証情報設定
インストールしたTomcatの`conf/tomcat-users.xml`を編集する。

## サーブレットのマッピング
これまで`@WebServlet`アノテーションの指定と`web.xml`へのサーブレットタグの指定を両方行っていたが（エラーが起きていた）、アノテーションのみの指定が正しいようだった。
`src/main/java/com/webapp`配下に以下のようなサーブレットクラスを作成してTomcatで実行→`/web-app/helloservlet`でアクセスできた

```java
package com.webapp;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/helloservlet")
public class HelloServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>Hello from MyServlet</h2>");
        out.println("</body></html>");
    }
}
```

## 参考
- [Javaのプロジェクトで使う.gitignore](https://qiita.com/tora470/items/d61c3fcb261e48edc996)
- [tomcatの設定 web.xmlのURLパターンにクラス名と同じ名前は使えない？](https://teratail.com/questions/122182)