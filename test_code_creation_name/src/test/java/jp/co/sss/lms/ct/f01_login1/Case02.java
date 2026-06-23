package jp.co.sss.lms.ct.f01_login1;

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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * 結合テスト ログイン機能①
 * ケース02
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース02 受講生 ログイン 認証失敗")
public class Case02 {

	private static WebDriver driver;

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

		//トップページを開く
		webDriver.get("http://localhost:8080/lms");

		//ログイン画面の検証
		WebElement tagElement = webDriver.findElement(By.tagName("h2"));
		assertEquals("ログイン", tagElement.getText());

		getEvidence(new Object() {
		});
	}

	@Test
	@Order(2)
	@DisplayName("テスト02 DBに登録されていないユーザーでログイン")
	void test02() {
		// TODO ここに追加

		//トップページを開く
		webDriver.get("http://localhost:8080/lms");

		//存在しない会員情報を入力
		webDriver.findElement(By.name("loginId")).sendKeys("testId");
		webDriver.findElement(By.name("password")).sendKeys("testPassword");

		//ログインボタンをクリック
		webDriver.findElement(By.className("btn-primary")).click();

		//メッセージの内容が正しいかの確認
		WebElement errorMsg = webDriver.findElement(By.className("help-inline"));
		assertEquals("* ログインに失敗しました。", errorMsg.getText());

		//スクリーンショットの撮影
		getEvidence(new Object() {
		});
	}

}
