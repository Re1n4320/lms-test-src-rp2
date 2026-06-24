package jp.co.sss.lms.ct.f02_faq;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import jp.co.sss.lms.ct.util.WebDriverUtils;

/**
 * 結合テスト よくある質問機能
 * ケース04
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース04 よくある質問画面への遷移")
public class Case04 {

	@BeforeAll
	static void beforeAll() {
		WebDriverUtils.createDriver();
	}

	@AfterAll
	static void afterAll() {
		WebDriverUtils.closeDriver();
	}

	@Test
	@Order(1)
	@DisplayName("テスト01 トップページURLでアクセス")
	void test01() {
		// TODO ここに追加
		//トップページにアクセス
		webDriver.get("http://localhost:8080/lms");

		//ログイン画面の検証
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

		//遷移後の画面に書かれている内容を検証
		WebElement welcomeMsg = webDriver.findElement(By.tagName("h2"));
		//テキストが含まれているかチェック
		assertTrue(welcomeMsg.getText().contains("DEMOコース"));

		//スクリーンショットの撮影
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(3)
	@DisplayName("テスト03 上部メニューの「ヘルプ」リンクからヘルプ画面に遷移")
	void test03() {
		// TODO ここに追加
		//詳細ページにアクセス
		webDriver.get("http://localhost:8080/lms/course/detail");

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
		//ヘルプページにアクセス
		webDriver.get("http://localhost:8080/lms/help");

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

}
