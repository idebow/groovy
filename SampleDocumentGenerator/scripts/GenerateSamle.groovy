package com.assistmicro.groovy.SampleDocumentGenerator

import com.assistmicro.groovy.SampleDocumentGenerator.SampleDocumentGeneratorMain;

SampleDocumentGeneratorMain s = new SampleDocumentGeneratorMain()

println "[" + s.getSystemDateString() + "] 処理を開始しました。" 

//パス指定時の注意 Windows系は\\
//ここにベースとするデータのファイルをセット
//s.setBaseSampleData("C:\\WORK\\testdata\\青空\\text\\01_hangan_chimatao_iku.txt")
s.setBaseSampleData("C:\\WORK\\testdata\\青空\\aozora_all.txt")
//ここに検索のキーにするデータを格納したファイルのパスを設定する
s.setSearchIdData("C:\\WORK\\testdata\\names_200k.txt")

/*----------------------------------------
サンプル作成のための設定
生成されるファイル数は
 (setDirectoriesParDirectoryのsetDirectoryHierarchyDepth乗*setFilesParDirectory)+ setFilesParDirectory
 ----------------------------------------*/
s.setMaxCreateFileNumber(100) //生成するファイル数の上限
s.setFilesParDirectory(200)//1フォルダあたりの生成ファイル数
s.setDirectoriesParDirectory(100)//1フォルダあたりの生成サブフォルダ数
s.setDirectoryHierarchyDepth(5) //最大階層深度
s.setOutFileLength(51200)//生成するファイルのサイズ

s.setOutFileEncoding("UTF-8") //ファイルのエンコード
//サンプルデータを出力するディレクトリのパスを指定して処理を開始する
//s.generateSampleStructure("/Users/999/Documents/home")
s.generateSampleStructure("C:\\WORK\\testdata\\files")

println "[" + s.getSystemDateString() + "] ファイル出力が完了しました。"

