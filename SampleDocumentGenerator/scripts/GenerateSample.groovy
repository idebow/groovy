package com.assistmicro.groovy.SampleDocumentGenerator

import com.assistmicro.groovy.SampleDocumentGenerator.SampleDocumentGeneratorMain;

SampleDocumentGeneratorMain s = new SampleDocumentGeneratorMain()

println "[" + s.getSystemDateString() + "] 処理を開始しました。"

//パス指定時の注意 Windows系は\\
//ここにベースとするデータのファイルをセット
s.setBaseSampleData("./basesampledata/aozora_all_UTF8.txt","UTF-8")
//ここに検索のキーにするデータを格納したファイルのパスを設定する
s.setSearchIdData("./basesampledata/names_200k.txt")
/*----------------------------------------
サンプル作成のための設定
生成されるファイル数は
 (setDirectoriesParDirectoryのsetDirectoryHierarchyDepth乗*setFilesParDirectory)+ setFilesParDirectory
 ----------------------------------------*/
s.setMaxCreateFileNumber(10000) //生成するファイル数の上限
s.setFilesParDirectory(200)//1フォルダあたりの生成ファイル数
s.setDirectoriesParDirectory(100)//1フォルダあたりの生成サブフォルダ数
s.setDirectoryHierarchyDepth(4) //最大階層深度
s.setOutFileLength(51200)//生成するファイルのサイズ

s.setOutFileEncoding("UTF-8") //ファイルのエンコード
//サンプルデータを出力するディレクトリのパスを指定して処理を開始する
s.setSearchKeyFile("/mnt/usbhdd/sampledata/ds1/searchkeypare.txt")
s.generate("/mnt/usbhdd/sampledata/ds1")
println "[" + s.getSystemDateString() + "] ds1のファイル出力が完了しました。"
println "[" + s.getSystemDateString() + "] 続けてds2のファイル作成処理に取りかかります。"
s.setSearchKeyFile("/mnt/usbhdd/sampledata/ds2/searchkeypare.txt")

s.generate("/mnt/usbhdd/sampledata/ds2")

println "[" + s.getSystemDateString() + "] ds2のファイル出力が完了しました。"