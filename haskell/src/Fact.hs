module Fact where

-- ���X�g : �K��
fact :: Integer -> Integer
fact n = if n == 0 then 1 else n * fact (n - 1)

-- �L�� = �̌��ŉ��s����ꍇ
{-
fact n =
  if n == 0 then 1
  else n * fact (n - 1)
-}

-- ���X�g : �t�B�{�i�b�`�֐�
fibo :: Integer -> Integer
fibo n =
  if n == 0 || n == 1 then 1
  else fibo (n - 1) + fibo (n - 2)

-- �p�^�[�����g���ꍇ
fibo' :: Integer -> Integer
fibo' 0 = 1
fibo' 1 = 1
fibo' n = fibo' (n - 1) + fibo' (n - 2)

-- ���X�g : �ݏ� (1)
pow :: (Integer, Integer) -> Integer
pow (x, y) =
  if y == 0 then 1 else x * pow (x, y - 1)

  