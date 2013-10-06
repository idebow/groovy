package com.assistmicro.groovy.SampleDocumentGenerator


import java.awt.TexturePaintContext.Int;
import java.lang.StringBuilder

import BufferedReader
import BufferedWriter

public class SampleDocumentGeneratorMain {

	//フィールド設定

	//生成ファイル数上限

	//最大階層数
	private Long directoryHierarchyDepth=4
	//ファイル/フォルダ番号共通カウンタ
	private Long fileDirNoConter = 1
	//マスタサンプルデータ
	private String sampleFile=""
	//1フォルダに生成するファイル数の上限 デフォルトは100
	private Long filesToBeCreate = 100
	//1フォルダに生成するフォルダ数の上限
	private Long directoriesToBeCreate=5
	//生成するファイルの長さ設定デフォルトは50KB
	private Long targetfileLength=51200

	//ファイルデリミタ
	private String FS = File.separator



	//ファイル生成のコンストラクタ
	public SampleDocumentGeneratorMain() {
		// TODO 自動生成されたコンストラクター・スタブ
	}


	//ディレクトリ構造体とファイルの一括生成
	public Void generateSampleStructure( String directoryPath, Long depth=1){
		BigDecimal fileCount=0
		BigDecimal dirCount=0
		//サンプルファイル作成
		//generateTextFiles(directoryPath)
		if  (depth >= directoryHierarchyDepth) {
			--depth
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
				generateSampleStructure(subDirectory,++depth)
			}
			
		}
	}

	//終了判定処理
	private boolean isTaskCompleted(){

	}

	//ファイル生成
	public generateTextFiles(String directoryPath){
		StringBuilder sb
		BufferedWriter bw
		//指定の個数を生成するまでループ
		for (Long i=1;i <=filesToBeCreate;i++){
			//サンプルファイルからデータを読み込んで新しいファイルへ書込む
			String filname = getNewFileName
			bw = new BufferedWriter(new FileWriter(new File(directoryPath + FS + filname)))
			for(;;){
				//マスターデータからサンプルデータ読み出し
				sb.append(SampleReader.getNextLine)
				sb.newLine
				//データ長チェック処理
				if(sb.length >=targetfileLength) {
					//最後に名前データを追加
					//ファイル書き込み
					bw.write(sb)
					//フラッシュ
					bw.flush
					bw.close
					sb.setLength(0)
					break
				}
			}
			//ファイルクローズ
		}
	}
	private String getNewFileName(){
		//最新のシリアル番号からファイル名を生成
		getFileDirSerialNo.toString() + ".txt"
	}
	private String getNewSubDirectoryName(){
		//最新のシリアル番号を取得
		this.getFileDirSerialNo().toString()
	}
	//ファイルとフォルダ共通のシリアル番号を返却する
	private Long getFileDirSerialNo(){
		fileDirNoConter++
	}

	//サンプルファイル読み込みクラス
	class SampleReader{
		//ファイルインプットストリーム
		private BufferedReader br
		public SampleReader(){

		}

		public String getNextLine(){
			String data = "test"
		}
	}
}