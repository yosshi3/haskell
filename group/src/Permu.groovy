

def makeSn
//　list順列からlistlist順列作成　 例）[1, 2, 3]から[[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]作成
makeSn = {List intList ->
	if (intList.size() == 0) {
		[[]]    // 空のlistlistを返す
	} else {
		def out = []                        // 空のlistを作成する
		for (int item : intList) {          // 先頭１番目でループ
			List copylist = intList.clone()
			copylist.remove((Object)item)   // itemで指定した数値を配列から削除
			List lists = makeSn(copylist) // 【変更前】[[2, 3], [3, 2]]
			for (List list : lists) {
				list.add(0, item)           // 先頭１番目を配列の先頭に付与
			}                               // 【変更後】[[1, 2, 3], [1, 3, 2]]
			out.addAll(lists)
		}
		out
	}
}
// 数値からlist-list配列順列の作成関数　例）[[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]
// 処理内容は、[1,2,..,n]の配列を作ってlistPermを呼出す
def Sn = {makeSn(1..it as ArrayList)}
println Sn(3)

List.metaClass.multiply = {List a -> delegate[a.collect{it - 1}]} //　＊演算定義、配列を使って入替
def a = [1, 3, 2, 4]
def b = [4, 1, 2, 3]
println "【${a} * ${b}】" + a * b

ArrayList.metaClass.isTranspose = {
	-> def ret = []
	delegate.eachWithIndex{it,i -> if(it!=i+1)ret << it} // 配列番号と配列数値が一致していない箇所を記録
	ret.size() == 2
}
println "【${a}isTranspose()】" + a.isTranspose() + "【${b}isTranspose()】" + b.isTranspose()

ArrayList.metaClass.e = {-> 1..delegate.size() as ArrayList} // 単位元を生成
ArrayList.metaClass.rep = {int tranA,int tranB ->
	def tmp = delegate[tranA]
	delegate[tranA] = delegate[tranB];delegate[tranB] = tmp
	delegate
} // 配列インデックス指定で入替
println "【${a}.e().rep(0,1)】" + a.e().rep(0,1)

ArrayList.metaClass.transpose = {
	-> def lists = [];def obj = delegate.clone()
	obj.eachWithIndex{it,i ->
		int ind = obj.findIndexOf{it == i + 1}
		if(i != ind){
			obj.rep(i,ind)
			lists.add(0, obj.e().rep(i,ind))
		}
	}
	lists
}	// 互換リスト作成

ArrayList.metaClass.act = {
	def obj = delegate[0].e()
	delegate.each{ obj = obj * it }
	obj
}
println "【${b}.transpose()】" + b.transpose() + "【${b}.transpose().act()】" + b.transpose().act()
