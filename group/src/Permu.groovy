
def makeSn
//　list順列からlistlist順列作成　 例）[1, 2, 3]から[[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]作成
makeSn = {List intList ->
	if (intList.size() == 0) {
		[[]] // 空のlistlistを返す
	} else {
		def out = [] // 空のlistを作成する
		for (int item : intList) { // 先頭１番目でループ
			List copylist = intList.clone()
			copylist.remove((Object)item) // itemで指定した数値を配列から削除
			List lists = makeSn(copylist) // 【変更前】[[2, 3], [3, 2]]
			for (List list : lists) {
				list.add(0, item) // 先頭１番目を配列の先頭に付与
			} // 【変更後】[[1, 2, 3], [1, 3, 2]]
			out.addAll(lists)
		}
		out
	}
}
// 数値からlist-list配列順列の作成関数　例）[[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]
// 処理内容は、[1,2,..,n]の配列を作う。
def Sn = {makeSn(1..it as ArrayList)}
println Sn(3)

List.metaClass.multiply = {delegate[it.collect{it - 1}]} //　＊演算定義、配列を使って入替
def (a,b) = [[1, 3, 2, 4], [4, 1, 2, 3]]
println "【${a} * ${b}】" + a * b

ArrayList.metaClass.isTranspose = {
	-> def ret = []
	delegate.eachWithIndex{it,i -> if(it!=i+1)ret << it} // 配列番号と配列数値が一致していない箇所を記録
	ret.size() == 2
} // 互換判定
println "【${a}.isTranspose()】" + a.isTranspose() + " 【${b}.isTranspose()】" + b.isTranspose()

ArrayList.metaClass.e = {-> 1..delegate.size() as ArrayList} // 単位元を生成

ArrayList.metaClass.rep = {int tranA, int tranB ->
	use(Collections){delegate.swap(tranA, tranB)}
	delegate
} // 入替

println "【${a}.e().rep(0,1)】" + a.e().rep(0,1)

ArrayList.metaClass.transpose = {
	-> def (lists, obj) = [[], delegate.clone()]
	obj.eachWithIndex{it, i ->
		int ind = obj.findIndexOf{it == i + 1}
		if(i != ind){
			obj.rep(i,ind)
			lists.add(0, obj.e().rep(i,ind))
		}
	}
	lists
} // 互換リスト作成

ArrayList.metaClass.act = {
	def obj = delegate[0].e(); delegate.each{obj = obj * it}; obj
} //　listlist内すべての要素に作用
println "【${b}.transpose()】" + b.transpose() + " 【${b}.transpose().act()】" + b.transpose().act()

ArrayList.metaClass.inv = {
	-> List inv = []; delegate.eachWithIndex{it, i -> inv << delegate.indexOf(i + 1) + 1}; inv
}
println "【${b}.inv()】" + b.inv() + " 【${b}.inv().inv()】" + b.inv().inv()


