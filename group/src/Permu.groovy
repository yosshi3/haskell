

def makeSn
//�@list���񂩂�listlist����쐬�@ ��j[1, 2, 3]����[[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]�쐬
makeSn = {List intList ->
	if (intList.size() == 0) {
		[[]]    // ���listlist��Ԃ�
	} else {
		def out = []                        // ���list���쐬����
		for (int item : intList) {          // �擪�P�ԖڂŃ��[�v
			List copylist = intList.clone()
			copylist.remove((Object)item)   // item�Ŏw�肵�����l��z�񂩂�폜
			List lists = makeSn(copylist) // �y�ύX�O�z[[2, 3], [3, 2]]
			for (List list : lists) {
				list.add(0, item)           // �擪�P�Ԗڂ�z��̐擪�ɕt�^
			}                               // �y�ύX��z[[1, 2, 3], [1, 3, 2]]
			out.addAll(lists)
		}
		out
	}
}
// ���l����list-list�z�񏇗�̍쐬�֐��@��j[[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]
// �������e�́A[1,2,..,n]�̔z��������listPerm���ďo��
def Sn = {makeSn(1..it as ArrayList)}
println Sn(3)

List.metaClass.multiply = {List a -> delegate[a.collect{it - 1}]} //�@�����Z��`�A�z����g���ē���
def a = [1, 3, 2, 4]
def b = [4, 1, 2, 3]
println "�y${a} * ${b}�z" + a * b

ArrayList.metaClass.isTranspose = {
	-> def ret = []
	delegate.eachWithIndex{it,i -> if(it!=i+1)ret << it} // �z��ԍ��Ɣz�񐔒l����v���Ă��Ȃ��ӏ����L�^
	ret.size() == 2
}
println "�y${a}isTranspose()�z" + a.isTranspose() + "�y${b}isTranspose()�z" + b.isTranspose()

ArrayList.metaClass.e = {-> 1..delegate.size() as ArrayList} // �P�ʌ��𐶐�
ArrayList.metaClass.rep = {int tranA,int tranB ->
	def tmp = delegate[tranA]
	delegate[tranA] = delegate[tranB];delegate[tranB] = tmp
	delegate
} // �z��C���f�b�N�X�w��œ���
println "�y${a}.e().rep(0,1)�z" + a.e().rep(0,1)

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
}	// �݊����X�g�쐬

ArrayList.metaClass.act = {
	def obj = delegate[0].e()
	delegate.each{ obj = obj * it }
	obj
}
println "�y${b}.transpose()�z" + b.transpose() + "�y${b}.transpose().act()�z" + b.transpose().act()
