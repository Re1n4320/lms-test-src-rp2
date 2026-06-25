package jp.co.sss.lms.ct.f02_faq;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * 結合テスト よくある質問機能
 * ケース06
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース06 カテゴリ検索 正常系")
public class Case06 {

	/** 前処理 */
	@BeforeAll
	static void before() {
		createDriver();
	}

	/** 後処理 */
	@AfterAll
	static void after() {
		closeDriver();
	}

	@Test
	@Order(1)
	@DisplayName("テスト01 トップページURLでアクセス")
	void test01() {
		// TODO ここに追加
		//ログイン画面の検証
		webDriver.get("http://localhost:8080/lms");
		WebElement tagElement = webDriver.findElement(By.tagName("h2"));
		assertEquals("ログイン", tagElement.getText());

		//スクリーンショットの撮影
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(2)
	@DisplayName("テスト02 初回ログイン済みの受講生ユーザーでログイン")
	void test02() {
		// TODO ここに追加
		//トップページにアクセス
		webDriver.get("http://localhost:8080/lms");

		//登録済みの会員情報を入力
		webDriver.findElement(By.name("loginId")).sendKeys("StudentAA01");
		webDriver.findElement(By.name("password")).sendKeys("Anon0316");

		//ログインボタンをクリック
		webDriver.findElement(By.className("btn-primary")).click();

		//タイトルを検証
		assertEquals("コース詳細 | LMS", webDriver.getTitle());

		//スクリーンショットの撮影
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(3)
	@DisplayName("テスト03 上部メニューの「ヘルプ」リンクからヘルプ画面に遷移")
	void test03() {
		// TODO ここに追加
		//「機能」ボタンをクリック
		webDriver.findElement(By.className("dropdown-toggle")).click();

		//「ヘルプ」のリンクをクリック
		webDriver.findElement(By.linkText("ヘルプ")).click();

		//タイトルを検証
		assertEquals("ヘルプ | LMS", webDriver.getTitle());

		//テキストが含まれているかの確認
		WebElement topMsg = webDriver.findElement(By.tagName("h2"));
		assertTrue(topMsg.getText().contains("ヘルプ"));

		//スクリーンショットの撮影
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「よくある質問」リンクからよくある質問画面を別タブに開く")
	void test04() {
		// TODO ここに追加
		// 現在開いている元のタブの識別子を覚えておく
		String originalWindow = webDriver.getWindowHandle();

		// 「よくある質問」のリンクをクリック、別タブが開く
		webDriver.findElement(By.linkText("よくある質問")).click();

		// 新しいタブが開くまで少し待つ
		for (String windowHandle : webDriver.getWindowHandles()) {
			if (!originalWindow.contentEquals(windowHandle)) {
				// 新しいタブに切り替える
				webDriver.switchTo().window(windowHandle);
				break;
			}
		}
		//タイトルを検証
		assertEquals("よくある質問 | LMS", webDriver.getTitle());

		//スクリーンショットの撮影
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 カテゴリ検索で該当カテゴリの検索結果だけ表示")
	void test05() {
		// TODO ここに追加
		//「【研修関係】」ボタンをクリック
		webDriver.findElement(By.linkText("【研修関係】")).click();

		// 検索結果
		List<WebElement> elements = webDriver.findElements((By.className("sorting_1")));

		//検索結果が2つであることの検証
		assertEquals(2, elements.size());

		//検索内容が正しいことの検証
		assertEquals("Q.研修の申し込みはどのようにすれば良いですか？", elements.get(1).getText());

		//スクリーンショットの撮影
		getEvidence(new Object() {
		});

	}

	@Test
	@Order(6)
	@DisplayName("テスト06 検索結果の質問をクリックしその回答を表示")
	void test06() {

		//質問内容の要素を特定する
		WebElement question = webDriver.findElement(By.className("mb10"));

		//遷移した画面に検索結果が映っていないため、見える位置までスクロールする
		((JavascriptExecutor) webDriver)
				.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", question);

		//要素が完全にクリック可能になるまで少し待つ
		WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(question));

		//質問内容をクリック
		question.click();

		//回答が表示されたことを確認する
		WebElement answer = webDriver.findElement(By.className("mb10"));
		wait.until(ExpectedConditions.visibilityOf(answer));

		//検索結果
		List<WebElement> elements = webDriver.findElements((By.className("sorting_1")));

		//検索結果が2つであることの検証
		assertEquals(2, elements.size());

		//検索結果に、指定した文字列が含まれているか、後方一致で確認
		String actualText = elements.get(0).getText();
		assertTrue(actualText.endsWith("協議という形を取らせて頂きます。 弊社営業担当までご相談下さい。"));

		//スクリーンショットの撮影
		getEvidence(new Object() {
		});

		System.out.println(elements.get(0).getText());
		//Q.キャンセル料・途中退校について
		//A. 受講者の退職や解雇等、やむを得ない事情による途中終了に関してなど、事情をお伺いした上で、協議という形を取らせて頂きます。 弊社営業担当までご相談下さい。

	}
}
