package jp.co.sss.lms.ct.f03_report;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * 結合テスト レポート機能
 * ケース07
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース07 受講生 レポート新規登録(日報) 正常系")
public class Case07 {

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

		//java概要の詳細ボタンをクリック
		webDriver.findElement(By.className("btn-primary")).click();

		//タイトルを検証
		assertEquals("コース詳細 | LMS", webDriver.getTitle());

		//スクリーンショットの撮影
		getEvidence(new Object() {
		});

	}

	@Test
	@Order(3)
	@DisplayName("テスト03 未提出の研修日の「詳細」ボタンを押下しセクション詳細画面に遷移")
	void test03() {
		// TODO ここに追加
		//ログインボタンをクリック
		//webDriver.findElement(By.className("w20per")).click();
		List<WebElement> elements = webDriver.findElements((By.className("w20per")));

		elements.get(5).click();

		//タイトルを検証
		assertEquals("セクション詳細 | LMS", webDriver.getTitle());

		//スクリーンショットの撮影
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「提出する」ボタンを押下しレポート登録画面に遷移")
	void test04() {
		// TODO ここに追加
		//ログインボタンをクリック
		List<WebElement> elements = webDriver.findElements((By.className("btn-default")));

		elements.get(1).click();

		//タイトルを検証
		assertEquals("レポート登録 | LMS", webDriver.getTitle());
		//WebElement tagElement = webDriver.findElement(By.className("btn-default"));
		//assertEquals("日報【デモ】を提出する", tagElement.getText());
		//
		//		//スクリーンショットの撮影
		//		getEvidence(new Object() {
		//		});
		//スクリーンショットの撮影
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 報告内容を入力して「提出する」ボタンを押下し確認ボタン名が更新される")
	void test05() {
		// TODO ここに追加
		//登録済みの会員情報を入力
		webDriver.findElement(By.name("contentArray[0]")).sendKeys("あ");

		//java概要の詳細ボタンをクリック
		webDriver.findElement(By.className("btn-primary")).click();
		//元の画面に遷移されたことを確認
		assertEquals("セクション詳細 | LMS", webDriver.getTitle());
		//スクリーンショットの撮影
		getEvidence(new Object() {
		});

	}

}
