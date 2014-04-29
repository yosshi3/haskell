
def makeSn
//@list‡—ñ‚©‚çlistlist‡—ñì¬@ —áj[1, 2, 3]‚©‚ç[[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]ì¬
makeSn = {List intList ->
	if (intList.size() == 0) {
		[[]] // ‹ó‚Ìlistlist‚ğ•Ô‚·
	} else {
		def out = [] // ‹ó‚Ìlist‚ğì¬‚·‚é
		for (int item : intList) { // æ“ª‚P”Ô–Ú‚Åƒ‹[ƒv
			List copylist = intList.clone()
			copylist.remove((Object)item) // item‚Åw’è‚µ‚½”’l‚ğ”z—ñ‚©‚çíœ
			List lists = makeSn(copylist) // y•ÏX‘Oz[[2, 3], [3, 2]]
			for (List list : lists) {
				list.add(0, item) // æ“ª‚P”Ô–Ú‚ğ”z—ñ‚Ìæ“ª‚É•t—^
			} // y•ÏXŒãz[[1, 2, 3], [1, 3, 2]]
			out.addAll(lists)
		}
		out
	}
}
// ”’l‚©‚çlist-list”z—ñ‡—ñ‚Ìì¬ŠÖ”@—áj[[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]
// ˆ—“à—e‚ÍA[1,2,..,n]‚Ì”z—ñ‚ğì‚¤B
def Sn = {makeSn(1..it as ArrayList)}
println Sn(3)

List.metaClass.multiply = {delegate[it.collect{it - 1}]} //@–‰‰Z’è‹`A”z—ñ‚ğg‚Á‚Ä“ü‘Ö
def (a,b) = [[1, 3, 2, 4], [4, 1, 2, 3]]
println "y${a} * ${b}z" + a * b

ArrayList.metaClass.isTranspose = {
	-> def ret = []
	delegate.eachWithIndex{it,i -> if(it!=i+1)ret << it} // ”z—ñ”Ô†‚Æ”z—ñ”’l‚ªˆê’v‚µ‚Ä‚¢‚È‚¢‰ÓŠ‚ğ‹L˜^
	ret.size() == 2
} // ŒİŠ·”»’è
println "y${a}.isTranspose()z" + a.isTranspose() + " y${b}.isTranspose()z" + b.isTranspose()

ArrayList.metaClass.e = {-> 1..delegate.size() as ArrayList} // ’PˆÊŒ³‚ğ¶¬

ArrayList.metaClass.rep = {int tranA, int tranB ->
	use(Collections){delegate.swap(tranA, tranB)}
	delegate
} // “ü‘Ö

println "y${a}.e().rep(0,1)z" + a.e().rep(0,1)

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
} // ŒİŠ·ƒŠƒXƒgì¬

ArrayList.metaClass.act = {
	def obj = delegate[0].e(); delegate.each{obj = obj * it}; obj
} //@listlist“à‚·‚×‚Ä‚Ì—v‘f‚Éì—p
println "y${b}.transpose()z" + b.transpose() + " y${b}.transpose().act()z" + b.transpose().act()

ArrayList.metaClass.inv = {
	-> List inv = []; delegate.eachWithIndex{it, i -> inv << delegate.indexOf(i + 1) + 1}; inv
}
println "y${b}.inv()z" + b.inv() + " y${b}.inv().inv()z" + b.inv().inv()


