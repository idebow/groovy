package com.assistmicro.groovy.SampleDocumentGenerator


import java.awt.TexturePaintContext.Int;
import java.lang.StringBuilder

public class SampleDocumentGeneratorMain {

	//フィールド設定

	//生成ファイル数上限

	//最大階層数
	private Long directoryHierarchyDepth=5
	//ファイル/フォルダ番号共通カウンタ
	private Long fileDirNoConter = 1
	//マスタサンプルデータ
	private String sampleFile=""
	//1フォルダに生成するファイル数の上限 デフォルトは100
	private Long filesToBeCreate = 3
	//1フォルダに生成するフォルダ数の上限
	private Long directoriesToBeCreate=5
	//生成するファイルの長さ設定デフォルトは50KB
	private Long targetfileLength=51200

	//ファイルデリミタ
	private String FS = File.separator

	private SampleReader sr
	
	//ファイル生成のコンストラクタ
	public SampleDocumentGeneratorMain() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public setBaseSampleData(String SampleDataPath){
		sr = new SampleReader(SampleDataPath)
	}
	
		//ディレクトリ構造体とファイルの一括生成
	public Void generateSampleStructure( String directoryPath, Long depth=1){
		BigDecimal fileCount=0
		BigDecimal dirCount=0

		++depth
		println depth.toString()
		//サンプルファイル作成
		for (Long i=1;i <=filesToBeCreate;i++){
			generateTextFile(directoryPath)
		}
		if  (depth >= directoryHierarchyDepth) {
			println "max depth"
			return
		} else {
			while (dirCount <=directoriesToBeCreate){
				//サブディレクトリの追加
				String subDirectory = directoryPath + FS + this.getNewSubDirectoryName()
				File fl = new File(subDirectory)
				fl.mkdirs()
				println subDirectory
				fl = null
				dirCount++
				//recursive call
				generateSampleStructure(subDirectory,depth)
			}
		}
	}

	//ファイル生成
	public generateTextFile(String directoryPath){
		StringBuilder sb = new StringBuilder()
		BufferedWriter bw
		//指定の個数を生成するまでループ
		//サンプルファイルからデータを読み込んで新しいファイルへ書込む
		String filname = getNewFileName()
		bw = new BufferedWriter(new FileWriter(new File(directoryPath + FS + filname)))
		Long fileLength=0
		for(;;){
			//マスターデータからサンプルデータ読み出し
			//				sb.append(SampleReader.getNextLine())
			fileLength = fileLength + ("テスト\n").getBytes().length
			sb.append("テスト\n")
			//データ長チェック処理
			if(fileLength >=targetfileLength) {
				break
			}
		}
		bw.write(sb.toString())
		bw.flush()
		bw.close()
		sb.setLength(0)
	}
	private String getNewFileName(){
		//最新のシリアル番号からファイル名を生成
		return getFileDirSerialNo().toString() + ".txt"
	}
	private String getNewSubDirectoryName(){
		//最新のシリアル番号を取得
		getFileDirSerialNo().toString()
	}
	//ファイルとフォルダ共通のシリアル番号を返却する
	private Long getFileDirSerialNo(){
		fileDirNoConter++
	}
	//終了判定処理
	private boolean isTaskCompleted(){
	}
}


////サンプルファイル読み込みクラス
//class SampleReader{
//	//ファイルインプットストリーム
//	private BufferedReader br
//	public SampleReader(){
//		null
//	}
//	public String getNextLine(){
//		return "test"
//	}
//}
