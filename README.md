# JAVA maven project
スッキリわかるサーブレット&JSPを参考にしつつ、サーブレットやJSPについて色々試すリポジトリ

## 環境
- VSCode: 1.81.1
- Java: 16.0.1
- Tomcat: 10.0.14
- Docker: 20.10.11

## Tomcat操作
起動
```bash
make up
```

停止
```bash
make down
```

- `localhost:8080/{application-name}`
- `localhost:8080/{application-name}/{servlet-name}`
でアクセスできる。

## Tomcat10からのAPI変更
Tomcat9までは`javax`パッケージを使用していたが、10からは`jakarta`を使用する必要がある。

> Users of Tomcat 10 onwards should be aware that, as a result of the move from Java EE to Jakarta EE as part of the transfer of Java EE to the Eclipse Foundation, the primary package for all implemented APIs has changed from javax.* to jakarta.*. This will almost certainly require code changes to enable applications to migrate from Tomcat 9 and earlier to Tomcat 10 and later. A migration tool has been developed to aid this process.

> Tomcat 10以降のユーザは、Java EEのEclipse Foundationへの移管の一環としてJava EEからJakarta EEに移行した結果、実装されているすべてのAPIのプライマリ・パッケージがjavax.*からjakarta.*に変更されたことに注意する必要があります。このため、アプリケーションをTomcat 9以前からTomcat 10以降に移行させるためには、ほぼ間違いなくコードの変更が必要になります。このプロセスを支援するための移行ツールが開発されています。

`pom.xml`にjakartaの依存関係を追加した。


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

## スコープ
- サーブレットクラスのインスタンスをJSPで利用するにはスコープを使う。
- 種類
  - ページスコープ
  - リクエストスコープ
  - セッションスコープ
  - アプリケーションスコープ
- 保存できるのはインスタンスのみ
- リクエストスコープを用いることでforward元とforward先でインスタンスの共有が可能になる

## DB接続
- PostgreSQL: 14を使用（Docker）

### JDBCドライバー
- `pom.xml`に依存関係として設定する
- `$CATALINA_HOME/lib`にjarファイルを配置する必要があるためコンテナ内にコピーした

### JDBCの接続情報について
- ローカルでは`host: localhost, port: 5434`で接続できるがJDBCではdocker内の接続情報を指定する必要があるようだ
- postgresコンテナとtomcatコンテナを通信させる必要があるため、dockerネットワークを作成した
- `docker network inspect {network name}`で出力されたpostgresのローカルIPアドレスとpostgresのデフォルトポート（5432）を指定して接続できた


## 参考
- [Javaのプロジェクトで使う.gitignore](https://qiita.com/tora470/items/d61c3fcb261e48edc996)
- [tomcatの設定 web.xmlのURLパターンにクラス名と同じ名前は使えない？](https://teratail.com/questions/122182)