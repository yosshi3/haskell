module Fact where

-- リスト : 階乗
fact :: Integer -> Integer
fact n = if n == 0 then 1 else n * fact (n - 1)

-- 記号 = の後ろで改行する場合
{-
fact n =
  if n == 0 then 1
  else n * fact (n - 1)
-}

-- リスト : フィボナッチ関数
fibo :: Integer -> Integer
fibo n =
  if n == 0 || n == 1 then 1
  else fibo (n - 1) + fibo (n - 2)

-- パターンを使う場合
fibo' :: Integer -> Integer
fibo' 0 = 1
fibo' 1 = 1
fibo' n = fibo' (n - 1) + fibo' (n - 2)

-- リスト : 累乗 (1)
pow :: (Integer, Integer) -> Integer
pow (x, y) =
  if y == 0 then 1 else x * pow (x, y - 1)

  