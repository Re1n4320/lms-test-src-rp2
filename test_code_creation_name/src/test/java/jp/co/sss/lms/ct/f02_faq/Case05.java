package jp.co.sss.lms.ct.f02_faq;

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
 * 結合テスト よくある質問機能
 * ケース05
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース05 キーワード検索 正常系")
public class Case05 {

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
	@DisplayName("テスト05 キーワード検索で該当キーワードを含む検索結果だけ表示")
	void test05() {
		// TODO ここに追加
		//文字を入力
		webDriver.findElement(By.name("keyword")).sendKeys("キャンセル");

		//「検索」ボタンをクリック
		webDriver.findElement(By.className("btn-primary")).click();

		// 検索結果
		List<WebElement> elements = webDriver.findElements(By.tagName("tbody"));

		//検索結果が1つのみであることの検証
		assertEquals(1, elements.size());

		//検索内容が正しいことの検証
		assertEquals("Q.キャンセル料・途中退校について", elements.get(0).getText());

		//スクリーンショットの撮影
		getEvidence(new Object() {
		});

	}

	@Test
	@Order(6)
	@DisplayName("テスト06 「クリア」ボタン押下で入力したキーワードを消去")
	void test06() {
		// TODO ここに追加
		//「クリア」ボタンを押す
		webDriver.findElement(By.xpath("//input[@type='button']")).click();

		//テキストボックスの要素を取得する
		WebElement searchInput = webDriver.findElement(By.name("keyword"));

		//入力欄の現在の値を「getAttribute("value")」で取得する
		String inputValue = searchInput.getAttribute("value");

		//値が「空（""）」であることを検証
		assertEquals("", inputValue);

		//スクリーンショットの撮影
		getEvidence(new Object() {
		});

	}

}